package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import service.DAOFactory;
import service.ProductDAO;

/**
 * Servlet implementation class OneProductController
 */
public class OneProductController extends HttpServlet {
	private static final String ONE_PRODUCT_FORM = "WEB-INF/views/oneProductView.jsp";

	public static final int MY_SQL = 1;
	private DAOFactory daoFactory;
	private ProductDAO productDAO;
	
    public OneProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(ONE_PRODUCT_FORM);
		daoFactory = DAOFactory.getInstance(MY_SQL);
		productDAO = daoFactory.getProductDAO();
		String productId = request.getParameter("prodId");
		Product product = null;
		if(productId!=null) {
			product = productDAO.getProductById(Integer.valueOf(productId));
		}
		request.setAttribute("product", product);
		rd.forward(request, response);
	}

}
