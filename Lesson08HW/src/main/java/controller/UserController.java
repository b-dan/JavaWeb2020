package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DBWork;

public class UserController extends HttpServlet{
	
	private static final String AUTH_FORM = "WEB-INF/views/loginView.jsp";
	//private static final String USER_AUTHORIZED = "WEB-INF/views/userView.jsp";
	private String errorText = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(AUTH_FORM);
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(AUTH_FORM);
		HttpSession session = req.getSession();

		if(req.getParameter("LogOut")!=null) {
			session.setAttribute("userAuthorized", null);
		}
		if(session.getAttribute("userAuthorized")==null) {
			String login = req.getParameter("f1");
			String password = req.getParameter("f2");
			DBWork dbWorker = new DBWork();
			User user = dbWorker.getName(login, password);
			if(user == null) {
				if(req.getParameter("LogOut")==null) {
					errorText = "Login or Password isn't correct";
					req.setAttribute("errorText", errorText);
				}
				//rd = req.getRequestDispatcher(AUTH_FORM);
			}else {
				req.setAttribute("user", user);
				session.setAttribute("userAuthorized", user);
			}
		}	
		rd.forward(req, resp);
	}
}
