package org.fin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fin.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.fin.model.Product;

@Controller
@RequestMapping("/products")
public class ProductController{
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
    public ProductController() {
        super();
    }
    
    @RequestMapping(method  = RequestMethod.GET)
	protected String doGet(@ModelAttribute ("Product") Product product, ModelMap modelMap,
			WebRequest request) {
		String category = request.getParameter("catId");
		Map<Product, Integer>cartProducts = (Map<Product, Integer>) request.getAttribute("cart", WebRequest.SCOPE_SESSION);
		List<Product> list = new ArrayList<Product>();
		if(category!=null) {
			list = productServiceImpl.getProductsByCategoryId(Integer.valueOf(category));
		}
		else {
			list= productServiceImpl.getProducts();
		}
		request.setAttribute("products", list, WebRequest.SCOPE_SESSION);
		request.setAttribute("cart", cartProducts, WebRequest.SCOPE_SESSION);
		return "productView";
	}

}
