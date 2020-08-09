package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.write("Hello from Servlet!");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("f1");
		String password = req.getParameter("f2");
		PrintWriter out = resp.getWriter();
		StringBuilder result = new StringBuilder(); 
		result.append("<!doctype html><html lang='en'>");
		if(login==null && password==null){
			}else{
			if(login.length()==0 && password.length()==0){
				result.append("Please input value of login and password first");
			}else{
				out.write("Login: "+login+"<br>");
				out.write("Password: "+password+"<br>");
				if(login.equals("admin")&& password.equals("123")){
					result.append("<div style='color:green'>Access granted!</div>");
				}
				else{
					result.append("<div style='color:red'>Access denied!</div>");
				}
			}
		}
		result.append("</html>");
		out.write(result.toString());
	}
}
