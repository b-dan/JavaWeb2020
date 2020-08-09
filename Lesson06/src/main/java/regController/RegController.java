package regController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import regModel.RegistrationUser;
import service.DBWork;



public class RegController extends HttpServlet{
	
	private StringBuilder errorText = new StringBuilder();
	private String [] genderArr = {"",""};
	private String [] addressArr = {"","",""};
	RegistrationUser regUser = new RegistrationUser();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/formRegView.jsp");
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
			errorText.append("<ul>");
			if(login.isEmpty()){
				errorText.append("<li>The 'login' is empty.</li>");
			}else{
				pattern = Pattern.compile("^(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})$");
				matcher = pattern.matcher(login);
				if(!matcher.matches()){
					errorText.append("<li>The 'login' must be an email.</li>");
				}else {
					regUser.setLogin(login);
				}
			}
			if(password.isEmpty()){
				errorText.append("<li>The 'password' is empty.</li>");
			}else {
				regUser.setPassword(password);
			}
			if(rePassword.isEmpty()){
				errorText.append("<li>The 'rePassword' is empty.</li>");
			}else{
				if(!password.equals(rePassword)){
					errorText.append("<li>The 'password' and 'rePassword' are not equals.</li>");
				}
				else{
					pattern = Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})$");
					matcher = pattern.matcher(password);
					if(!matcher.matches()){
						errorText.append("<li>Your 'password' do not fit the requirements.</li>");
					}else {
						regUser.setPassword(password);
						regUser.setRePassword(rePassword);
					}
				}
			}
			if(name.isEmpty()){
				errorText.append("<li>The 'name' is empty.</li>");
			}else {
				regUser.setName(name);
			}
			if(gender.isEmpty()){
				errorText.append("<li>The 'gender' is empty.</li>");
			}else {
				if(gender.equals("male")) {
					genderArr[0]= "checked";
				}else {
					genderArr[1]= "checked";
				}
			}
			if(address.isEmpty()){
				errorText.append("<li>The 'address' is empty.</li>");
			}else {
				if(address.equals("lnr")) {
					addressArr[0] = "selected";
				}
				if(address.equals("dnr")) {
					addressArr[1] = "selected";
				}
				if(address.equals("crimea")) {
					addressArr[2] = "selected";
				}
			}
			if(comment.isEmpty()){
				errorText.append("<li>The 'comment' is empty.</li>");
			}else {
				regUser.setComment(comment);
			}
			if(agree == null){
				errorText.append("<li>The 'agree' is empty.</li>");
			}
			req.setAttribute("regUser", regUser);
			req.setAttribute("genderArr", genderArr);
			req.setAttribute("addressArr", addressArr);
			if(errorText.toString().equals("<ul>")){
				DBWork dbWorker = new DBWork();
				dbWorker.setUser(login, password, name, rePassword, gender, address, comment, agree);
				rd = req.getRequestDispatcher("WEB-INF/views/regSuccess.jsp");
			}
			else{
				errorText.append("</ul>");
				req.setAttribute("errorText", errorText.toString());
				rd = req.getRequestDispatcher("WEB-INF/views/formRegView.jsp");

			}
		}
		rd.forward(req, resp);
		errorText.setLength(0);
	}
}
