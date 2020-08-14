package service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductService extends SQLConnection{
	private final String SELECT_PRODUCTS = "SELECT * FROM `products`";
	
	
	public ProductService() {
		super();
	}


	public List<Product> getProducts() {
		List<Product> products= new ArrayList<Product>();
		Connection con = getConnection();
		try(Statement statement = con.createStatement();){
			ResultSet rs = statement.executeQuery(SELECT_PRODUCTS);
			while(rs.next()) {
				Product p = new Product().setId(rs.getInt("id")).setPrice(rs.getInt("price"))
						.setDescription(rs.getString("description")).setCategory(rs.getInt("category"))
						.setName(rs.getString("name"));
				products.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return products;

	}
	
	
	/*public static void main(String[] args) {
		ProductService ps = new ProductService();
		List<Product> list = ps.getProducts();
		System.out.println(list);
		
	}*/
}
