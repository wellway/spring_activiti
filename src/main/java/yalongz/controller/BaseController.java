package yalongz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import yalongz.common.ConfigurationHelper;

/**
 * 
 * 项目名称：控制器基类 yalongz
 *
 */
@Controller
public class BaseController {
	protected Logger				logger	= LoggerFactory.getLogger(getClass());
	@Autowired
	protected ConfigurationHelper	configurationHelper;

	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 
	 * @param binder
	 */
	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	// // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
	// binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
	// @Override
	// public void setAsText(String text) {
	// setValue(text == null ? null : StringEscapeUtils
	// .escapeHtml4(text.trim()));
	// }
	//
	// @Override
	// public String getAsText() {
	// Object value = getValue();
	// return value != null ? value.toString() : "";
	// }
	// });
	// }

	/**
	 * 重定向至地址 url
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	protected String redirectTo(String url) {
		StringBuilder rto = new StringBuilder("redirect:");
		rto.append(url);
		return rto.toString();
	}

	/**
	 * 获取页面传来的参数，为空则为默认值
	 */
	protected String getUrlParam(HttpServletRequest request, String name,
			String defValue) {
		if (request.getParameter(name) == null
				|| StringUtils.isEmpty(request.getParameter(name))) {
			return defValue;
		} else {
			return request.getParameter(name).trim();
		}
	}

	/**
	 * 获取页面传来的参数，为空则为默认值
	 */
	protected String getUrlParamDefaultNull(HttpServletRequest request,
			String name) {
		if (request.getParameter(name) == null
				|| StringUtils.isEmpty(request.getParameter(name).trim())) {
			return null;
		} else {
			return request.getParameter(name);
		}
	}

	/**
	 * 设置一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	protected void setSessionAttribute(HttpServletRequest request, String sessionKey, Object objSessionAttribute) {
		HttpSession session = request.getSession();
		if (session != null)
			session.setAttribute(sessionKey, objSessionAttribute);
	}

	/**
	 * 移除Session对象属性值
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	protected void removeSessionAttribute(HttpServletRequest request, String sessionKey) {
		HttpSession session = request.getSession();
		if (session != null)
			session.removeAttribute(sessionKey);
	}

	/**
	 * 获取一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	protected Object getSessionAttribute(HttpServletRequest request, String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}
	
}
