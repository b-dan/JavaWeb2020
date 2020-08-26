package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PrintWriter out = response.getWriter();
		String productId = request.getParameter("prodId");
		String deleteId = request.getParameter("deleteById");
		String amountStr = request.getParameter("amount");
		HttpSession session = request.getSession();
		Map<Product, Integer> cartProducts = new HashMap<>();
		int qntAll = 0;
		
		if(session.getAttribute("cart")!=null) {
			cartProducts = (Map<Product, Integer>)session.getAttribute("cart");
			qntAll = (int) session.getAttribute("qntAll");
		}
		daoFactory = DAOFactory.getInstance(MY_SQL);
		productDAO = daoFactory.getProductDAO();
		Product tmpProd = null;
		int amount = 0;
		if(productId!=null) {
			amount = Integer.valueOf(amountStr);
			qntAll += amount;
			tmpProd = productDAO.getProductById(Integer.valueOf(productId));
			if(cartProducts.get(tmpProd)!=null) {
				amount = cartProducts.get(tmpProd)+amount;
			}
			cartProducts.put(tmpProd,amount);
			session.setAttribute("cart", cartProducts);
			session.setAttribute("qntAll", qntAll);
			out.write(String.valueOf(qntAll));
			
		}

		if(deleteId!=null) {
			Map<Product, Integer> afterDeleteCart = (Map<Product, Integer>)session.getAttribute("cart");
			//afterDeleteCart.stream().filter(p -> p.getId() == Integer.valueOf(deleteId)).findFirst().ifPresent(p -> afterDeleteCart.remove(p));
			tmpProd = productDAO.getProductById(Integer.valueOf(deleteId));
			amount = afterDeleteCart.get(tmpProd);
			qntAll-= amount;
			cartProducts.remove(tmpProd);
			session.setAttribute("cart", afterDeleteCart);
			session.setAttribute("qntAll", qntAll);
			out.write(qntAll);
			response.sendRedirect("./cart");
		}
	}

}
