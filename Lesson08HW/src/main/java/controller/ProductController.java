package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.ProductService;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(PRODUCT_FORM);
		ProductService ps = new ProductService();
		String category = request.getParameter("catId");
		List<Product> list = new ArrayList<Product>();
		if(category!=null) {
				//list = ps.getProductsByCategoryId(Integer.parseInt(category));
			list = ps.getProductsByCategoryId(Integer.valueOf(category));
		}
		else {
			list=ps.getProducts();
		}
		request.setAttribute("products", list);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
