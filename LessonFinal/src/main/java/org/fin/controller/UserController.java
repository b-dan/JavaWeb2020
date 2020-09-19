package org.fin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fin.model.User;
import org.fin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/login")
@SessionAttributes(value= {"login", "password"})
public class UserController{
	
	@Autowired
	UserServiceImpl userService;
	
	
	private String errorText = "";

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet() {
		return "loginView";
		
	}
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@ModelAttribute ("User") User user, ModelMap modelMap,
			@RequestParam (value = "LogOut", defaultValue = "") String logout,
			@RequestParam (value = "f1", defaultValue = "") String login,
			@RequestParam (value = "f2", defaultValue = "") String password,
			WebRequest req){
		errorText=null;
		if(!logout.equals("")) {
			user = new User();
			req.setAttribute("userAuthorized", null, WebRequest.SCOPE_SESSION);

		}
		if(req.getAttribute("userAuthorized", WebRequest.SCOPE_SESSION)==null) {
			user = userService.getName(login, password);
			if(user==null) {
				if(logout.equals("")) {
					errorText = "Login or Password isn't correct";
					req.setAttribute("errorText", errorText,WebRequest.SCOPE_SESSION);
				}
			}else {
				req.setAttribute("user", user,WebRequest.SCOPE_SESSION);
				req.setAttribute("userAuthorized", user, WebRequest.SCOPE_SESSION);
			}
		}
		return "loginView";
	}
}
