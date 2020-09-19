package org.fin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.fin.model.User;
import org.fin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;




@Controller
@RequestMapping("/registration")
public class RegController{
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	private static final String CHECKED = "checked";
	private static final String SELECTED = "selected";
	private List <String> errorText = new ArrayList<String>();
	private String [] genderArr = {"",""};
	private String [] addressArr = {"","",""};
	
	
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap modelMap) {
		modelMap.addAttribute("User", new User());
		return "regView";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@ModelAttribute ("User") User user, ModelMap modelMap,
			WebRequest req) {
		Pattern pattern = null;
		Matcher matcher = null;
		
		
		String login = user.getLogin();
		String password = user.getPassword();
		String rePassword = user.getRePassword();
		String name = user.getName();
		String gender = user.getGender();
		String address = user.getAddress();
		String comment = user.getComment();
		String agree = user.getAgree();
		errorText.clear();
		if(login!=null){
			if(login.isEmpty()){
				errorText.add("The 'login' is empty.");
			}else{
				pattern = Pattern.compile("^(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})$");
				matcher = pattern.matcher(login);
				if(!matcher.matches()){
					errorText.add("The 'login' must be an email.");
					user.setLogin(login);
				}else {
					user.setLogin(login);
				}
			}
			if(password.isEmpty()){
				errorText.add("The 'password' is empty.");
			}else {
				user.setPassword(password);
			}
			if(rePassword.isEmpty()){
				errorText.add("The 'rePassword' is empty.");
			}else{
				if(!password.equals(rePassword)){
					errorText.add("The 'password' and 'rePassword' are not equal.");
					user.setRePassword(rePassword);
				}
				else{
					pattern = Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})$");
					matcher = pattern.matcher(password);
					if(!matcher.matches()){
						errorText.add("Your 'password' do not fit the requirements.");
					}else {
						user.setPassword(password);
						user.setRePassword(rePassword);
					}
				}
			}
			if(name.isEmpty()){
				errorText.add("The 'name' is empty.");
			}else {
				user.setName(name);
			}
			if(gender.isEmpty()){
				errorText.add("The 'gender' is empty.");
			}else {
				if(gender.equals("male")) {
					genderArr[0]= CHECKED;
				}else {
					genderArr[1]= CHECKED;
				}
			}
			if(address.isEmpty()){
				errorText.add("The 'address' is empty.");
			}else {
				if(address.equals("lnr")) {
					addressArr[0] = SELECTED;
				}
				if(address.equals("dnr")) {
					addressArr[1] = SELECTED;
				}
				if(address.equals("crimea")) {
					addressArr[2] = SELECTED;
				}
			}
			if(comment.isEmpty()){
				errorText.add("The 'comment' is empty.");
			}else {
				user.setComment(comment);
			}
			if(agree == null){
				errorText.add("The 'agree' is empty.");
			}
			req.setAttribute("regUser", user, WebRequest.SCOPE_SESSION);
			req.setAttribute("genderArr", genderArr, WebRequest.SCOPE_SESSION);
			req.setAttribute("addressArr", addressArr, WebRequest.SCOPE_SESSION);
			
			
			
			if(errorText.size()==0){
				
				userServiceImpl.setUser(user);
				return "regSuccess";
			}
			else{
				req.setAttribute("errorText", errorText, WebRequest.SCOPE_SESSION);
				return "regView";

			}
		}
		return "regView";
	}
}
