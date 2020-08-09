package docControllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import docModels.Doc;

public class DocController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Doc doc = new Doc("Doc1","Type1");
		req.setAttribute("doc", doc);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/docView.jsp");
		rd.forward(req, resp);
		
	}
}
