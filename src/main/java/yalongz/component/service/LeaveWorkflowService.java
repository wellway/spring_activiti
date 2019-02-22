package yalongz.component.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.transaction.annotation.Transactional;

import yalongz.common.Page;
import yalongz.entity.Leave;

/**
 * 请假流程Service
 *
 * @author HenryYan
 */

public interface LeaveWorkflowService extends BaseService {
	/**
	 * 启动流程
	 *
	 * @param entity
	 */
	public ProcessInstance startWorkflow(Leave entity, Map<String, Object> variables);

	/**
	 * 查询待办任务
	 *
	 * @param userId
	 *            用户ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Leave> findTodoTasks(String userId, Page<Leave> page, int[] pageParams);

	/**
	 * 读取运行中的流程
	 *
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Leave> findRunningProcessInstaces(Page<Leave> page, int[] pageParams);

	/**
	 * 读取已结束中的流程
	 *
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Leave> findFinishedProcessInstaces(Page<Leave> page, int[] pageParams);

	/**
	 * 查询流程定义对象
	 *
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId);
}
