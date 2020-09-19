package org.fin.service;

import java.util.List;

import org.fin.model.Product;

public interface ProductService {
	List<Product> getProducts();
	List<Product> getProductsByCategoryId(int categoryId);
	Product getProductById(int id);

}
