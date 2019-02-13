package yalongz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yalongz.entity.User;
import yalongz.mapper.UserMapper;

@Controller
public class LoginController {

	@Autowired
	private UserMapper	userMapper;

	@RequestMapping("/index.do")
	@ResponseBody
	public User index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		User user = userMapper.selectById("1");
		return user;
	}

}
