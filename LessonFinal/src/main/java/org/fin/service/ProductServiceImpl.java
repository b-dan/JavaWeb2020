package org.fin.service;

import java.util.List;

import org.fin.dao.ProductDAO;
import org.fin.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO productDAO;

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public List<Product> getProductsByCategoryId(int categoryId) {
		return productDAO.getProductsByCategoryId(categoryId);
	}

	@Override
	public Product getProductById(int id) {
		return productDAO.getProductById(id);
	}

}
