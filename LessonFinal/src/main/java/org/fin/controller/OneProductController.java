package org.fin.controller;


import org.fin.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import org.fin.model.Product;

@Controller
@RequestMapping("/oneproduct")
public class OneProductController{
	
	@Autowired
	ProductServiceImpl productServiceImpl;
    public OneProductController() {
        super();
    }
    
    @RequestMapping(method = RequestMethod.GET)
	protected String doGet(@ModelAttribute ("Product") Product product, ModelMap modelMap,
			WebRequest request) {
		
		String productId = request.getParameter("prodId");
		product = null;
		Map<Product, Integer>cartProducts = (Map<Product, Integer>) request.getAttribute("cart", WebRequest.SCOPE_SESSION);
		if(productId!=null) {
			product = productServiceImpl.getProductById(Integer.valueOf(productId));
		}
		request.setAttribute("product", product, WebRequest.SCOPE_SESSION);
		request.setAttribute("cart", cartProducts, WebRequest.SCOPE_SESSION);
		return "oneProductView";
	}

}
