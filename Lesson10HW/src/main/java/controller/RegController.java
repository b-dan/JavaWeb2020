package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.User;
import service.DAOFactory;
import service.UserDAO;



public class RegController extends HttpServlet{
	
	private static final String REG_FORM = "WEB-INF/views/regView.jsp";
	private static final String REG_SUCCESS = "WEB-INF/views/regSuccess.jsp";
	private static final String CHECKED = "checked";
	private static final String SELECTED = "selected";
	private List <String> errorText = new ArrayList<String>();
	private String [] genderArr = {"",""};
	private String [] addressArr = {"","",""};
	public static final int MY_SQL = 1;
	private DAOFactory daoFactory;
    private UserDAO userDao;
	User user = new User();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(REG_FORM);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Pattern pattern = null;
		Matcher matcher = null;
		RequestDispatcher rd = null;
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String comment = req.getParameter("comment");
		String agree = req.getParameter("agree");
		
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
					errorText.add("The 'password' and 'rePassword' are not equals.");
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
			req.setAttribute("regUser", user);
			req.setAttribute("genderArr", genderArr);
			req.setAttribute("addressArr", addressArr);
			if(errorText.size()==0){
				daoFactory = DAOFactory.getInstance(MY_SQL);
				userDao = daoFactory.getUserDAO();
				userDao.setUser(user);
				rd = req.getRequestDispatcher(REG_SUCCESS);
			}
			else{
				req.setAttribute("errorText", errorText);
				rd = req.getRequestDispatcher(REG_FORM);

			}
		}
		rd.forward(req, resp);
		errorText.clear();
	}
}
