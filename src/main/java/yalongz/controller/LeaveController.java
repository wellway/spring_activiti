package yalongz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;







import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yalongz.common.Constants;
import yalongz.common.Page;
import yalongz.common.PageUtil;
import yalongz.common.Variable;
import yalongz.component.service.LeaveWorkflowService;
import yalongz.entity.Leave;
import yalongz.mapper.LeaveMapper;

@Controller
@RequestMapping(value = "/oa/leave")
public class LeaveController {

	private Logger					logger	= LoggerFactory.getLogger(getClass());

	@Autowired
	protected RuntimeService		runtimeService;

	@Autowired
	protected TaskService			taskService;
	@Autowired
	private IdentityService			identityService;
	@Autowired
	protected LeaveWorkflowService	workflowService;
	@Autowired
	private LeaveMapper  leaveMapper;

	/**
	 * 启动请假流程
	 *
	 * @param leave
	 */
	@RequestMapping(value = "start")
	@ResponseBody
    public User startWorkflow(HttpServletRequest req) {
		User user =null;
		try {
             user = identityService.createUserQuery().userId(Constants.USER_ID).singleResult();
            // 用户未登录不能操作，实际应用使用权限框架实现，例如Spring Security、Shiro等
//            if (user == null || StringUtils.isBlank(user.getId())) {
//                return "redirect:/login?timeout=true";
//            }
             Leave   leave = new Leave(new Date(), new Date(), "年假", "去看漂亮的妹子");
            leave.setUserId(user.getId());
            Map<String, Object> variables = new HashMap<String, Object>();
            ProcessInstance processInstance = workflowService.startWorkflow(leave, variables);
//            redirectAttributes.addFlashAttribute("message", "流程已启动，流程ID：" + processInstance.getId());
        } catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
//                redirectAttributes.addFlashAttribute("error", "没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                logger.error("启动请假流程失败：", e);
//                redirectAttributes.addFlashAttribute("error", "系统内部错误！");
            }
        } catch (Exception e) {
            logger.error("启动请假流程失败：", e);
//            redirectAttributes.addFlashAttribute("error", "系统内部错误！");
        }
        return user;
    }
	
	 /**
     * 任务列表
     *
     * @param leave
     */
    @RequestMapping(value = "list/task")
    public ModelAndView taskList(HttpSession session, HttpServletRequest request) {
        
    	ModelAndView mav = new ModelAndView("/oa/leave/taskList");
        Page<Leave> page = new Page<Leave>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);

//        String userId = UserUtil.getUserFromSession(session).getId();
        String userId = Constants.USER_ID;
        workflowService.findTodoTasks(userId, page, pageParams);
        mav.addObject("page", page);
        return mav;
    }

    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "list/running")
    public ModelAndView runningList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/oa/leave/running");
        Page<Leave> page = new Page<Leave>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);
        workflowService.findRunningProcessInstaces(page, pageParams);
        mav.addObject("page", page);
        return mav;
    }

    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "list/finished")
    public ModelAndView finishedList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/oa/leave/finished");
        Page<Leave> page = new Page<Leave>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);
        workflowService.findFinishedProcessInstaces(page, pageParams);
        mav.addObject("page", page);
        return mav;
    }

    /**
     * 签收任务
     */
    @RequestMapping(value = "task/claim/{id}")
    public String claim(@PathVariable("id") String taskId, HttpSession session, RedirectAttributes redirectAttributes) {
//        String userId = UserUtil.getUserFromSession(session).getId();
    	String userId = Constants.USER_ID;
        taskService.claim(taskId, userId);
        redirectAttributes.addFlashAttribute("message", "任务已签收");
        return "redirect:/oa/leave/list/task";
    }

    /**
     * 读取详细数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detail/{id}")
    @ResponseBody
    public Leave getLeave(@PathVariable("id") Long id) {
//        Leave leave = leaveManager.getLeave(id);
        Leave leave = leaveMapper.selectById(id);
        return leave;
    }

    /**
     * 读取详细数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detail-with-vars/{id}/{taskId}")
    @ResponseBody
    public Leave getLeaveWithVars(@PathVariable("id") Long id, @PathVariable("taskId") String taskId) {
//        Leave leave = leaveManager.getLeave(id);
    	 Leave leave = leaveMapper.selectById(id);
        Map<String, Object> variables = taskService.getVariables(taskId);
        leave.setVariables(variables);
        return leave;
    }

    /**
     * 完成任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "complete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String complete(@PathVariable("id") String taskId, Variable var) {
        try {
            Map<String, Object> variables = var.getVariableMap();
            taskService.complete(taskId, variables);
            return "success";
        } catch (Exception e) {
            logger.error("error on complete task {}, variables={}", new Object[]{taskId, var.getVariableMap(), e});
            return "error";
        }
    }
}
