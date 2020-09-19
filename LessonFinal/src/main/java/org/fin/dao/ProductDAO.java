package org.fin.dao;

import java.util.List;

import org.fin.model.Product;

public interface ProductDAO {
	List<Product> getProducts();
	List<Product> getProductsByCategoryId(int categoryId);
	Product getProductById(int id);

}
