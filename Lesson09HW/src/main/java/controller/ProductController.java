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
import service.DAOFactory;
import service.ProductDAO;

public class ProductController extends HttpServlet {
	private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";
       
	public static final int MY_SQL = 1;
	private DAOFactory daoFactory;
	private ProductDAO productDAO;
	
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(PRODUCT_FORM);
		daoFactory = DAOFactory.getInstance(MY_SQL);
		productDAO = daoFactory.getProductDAO();
		String category = request.getParameter("catId");
		List<Product> list = new ArrayList<Product>();
		if(category!=null) {
				//list = ps.getProductsByCategoryId(Integer.parseInt(category));
			list = productDAO.getProductsByCategoryId(Integer.valueOf(category));
		}
		else {
			list=productDAO.getProducts();
		}
		request.setAttribute("products", list);
		rd.forward(request, response);
	}

}
