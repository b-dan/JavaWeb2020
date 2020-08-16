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
import service.ProductService;

/**
 * Servlet implementation class OneProductController
 */
public class OneProductController extends HttpServlet {
	private static final String ONE_PRODUCT_FORM = "WEB-INF/views/oneProductView.jsp";

    public OneProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(ONE_PRODUCT_FORM);
		String productId = request.getParameter("prodId");
		ProductService ps = new ProductService();
		Product product = null;
		if(productId!=null) {
			product = ps.getProductById(Integer.valueOf(productId));
		}
		request.setAttribute("product", product);
		rd.forward(request, response);
	}

}
