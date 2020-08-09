package userController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DBWork;
import userModel.User;

public class UserController extends HttpServlet{

	private String errorText = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/formUserView.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String login = req.getParameter("f1");
		String password = req.getParameter("f2");
		DBWork dbWorker = new DBWork();
		User user = dbWorker.getName(login, password);
		if(user == null) {
			errorText = "Login or Password isn't correct";
			req.setAttribute("errorText", errorText);
			rd = req.getRequestDispatcher("WEB-INF/views/formUserView.jsp");
		}else {
			req.setAttribute("user", user);
			rd = req.getRequestDispatcher("WEB-INF/views/userView.jsp");
		}
		rd.forward(req, resp);
	}
}
