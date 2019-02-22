package yalongz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yalongz.entity.UserInfo;
import yalongz.mapper.UserMapper;

@Controller
public class LoginController {

	@Autowired
	private UserMapper	userMapper;

	@RequestMapping("/index.do")
	@ResponseBody
	public UserInfo index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		UserInfo user = userMapper.selectById("11");
		return user;
	}

}
