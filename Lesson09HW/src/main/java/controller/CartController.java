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

public class CartController extends HttpServlet{
	private static final String CART_FORM = "WEB-INF/views/cartView.jsp";

	public static final int MY_SQL = 1;
	private DAOFactory daoFactory;
	private ProductDAO productDAO;

	public CartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(CART_FORM);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("prodId");
		String deleteId = request.getParameter("deleteById");
		HttpSession session = request.getSession();
		List<Product> cartProducts = new ArrayList<Product>();
		if(session.getAttribute("cart")!=null) {
			cartProducts = (List<Product>)session.getAttribute("cart");
		}
		if(productId!=null) {
			daoFactory = DAOFactory.getInstance(MY_SQL);
			productDAO = daoFactory.getProductDAO();
			cartProducts.add(productDAO.getProductById(Integer.valueOf(productId)));

			session.setAttribute("cart", cartProducts);

			response.sendRedirect("./products");
		}

		if(deleteId!=null) {
			List<Product> afterDeleteCart = (List<Product>)session.getAttribute("cart");

			afterDeleteCart.stream().filter(p -> p.getId() == Integer.valueOf(deleteId)).findFirst().ifPresent(p -> afterDeleteCart.remove(p));
			session.setAttribute("cart", afterDeleteCart);

			response.sendRedirect("./cart");
		}
	}

}
