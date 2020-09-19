package org.fin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fin.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import org.fin.model.Product;

@Controller
@RequestMapping("/cart")
public class CartController{

	@Autowired
	ProductServiceImpl productServiceImpl;
	
	public CartController() {
		super();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap modelMap, WebRequest request) {
		return "cartView";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@ModelAttribute ("Product") Product product, ModelMap modelMap,
			WebRequest request,
			@RequestParam (value = "prodId", defaultValue = "test") String productId,
			@RequestParam (value = "deleteById", defaultValue = "test") String deleteId,
			@RequestParam (value = "amount", defaultValue = "1") String amountStr,
			HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<Product, Integer> cartProducts = new HashMap<>();
		int qntAll = 0;
		if(request.getAttribute("cart", WebRequest.SCOPE_SESSION)!=null) {
			cartProducts = (Map<Product, Integer>) request.getAttribute("cart", WebRequest.SCOPE_SESSION);
			qntAll = (int) request.getAttribute("qntAll", WebRequest.SCOPE_SESSION);
		}
		Product tmpProd = null;
		int amount = 0;
		if(!productId.equals("test")) {
			amount = Integer.valueOf(amountStr);
			qntAll += amount;
			tmpProd = productServiceImpl.getProductById(Integer.valueOf(productId));
			if(cartProducts.get(tmpProd)!=null) {
				amount = cartProducts.get(tmpProd)+amount;
			}
			cartProducts.put(tmpProd,amount);
			request.setAttribute("cart", cartProducts, WebRequest.SCOPE_SESSION);
			request.setAttribute("qntAll", qntAll, WebRequest.SCOPE_SESSION);
			out.write(String.valueOf(qntAll));
		}

		if(!deleteId.equals("test")) {
			Map<Product, Integer> afterDeleteCart = (Map<Product, Integer>) request.getAttribute("cart", WebRequest.SCOPE_SESSION);
			//afterDeleteCart.stream().filter(p -> p.getId() == Integer.valueOf(deleteId)).findFirst().ifPresent(p -> afterDeleteCart.remove(p));
			tmpProd = productServiceImpl.getProductById(Integer.valueOf(deleteId));
			amount = afterDeleteCart.get(tmpProd);
			qntAll-= amount;
			cartProducts.remove(tmpProd);
			request.setAttribute("cart", afterDeleteCart, WebRequest.SCOPE_SESSION);
			request.setAttribute("qntAll", qntAll, WebRequest.SCOPE_SESSION);			
			out.write(String.valueOf(qntAll));
			return "redirect:/cart";
		}
		return null;
	}

}
