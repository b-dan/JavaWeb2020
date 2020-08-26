package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import service.ProductDAO;

public class MySQLProductDAO implements ProductDAO{

	private final String SELECT_PRODUCTS = "SELECT * FROM `products`";
	private final String SELECT_PRODUCTS_BY_CATEGORY = "SELECT * FROM PRODUCTS WHERE CATEGORY=?";
	private final String SELECT_PRODUCT_BY_ID = "SELECT * FROM PRODUCTS WHERE ID=?";
	private MySqlDAOFactory mySqlDAOFactory;
	
	public MySQLProductDAO(MySqlDAOFactory mySqlDAOFactory) {
		this.mySqlDAOFactory = mySqlDAOFactory;
	}
	
	@Override
	public List<Product> getProducts() {
		List<Product> products= new ArrayList<Product>();
		Connection con = mySqlDAOFactory.getConnection();
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
	
	@Override
	public List<Product> getProductsByCategoryId(int categoryId) {
		List<Product> products= new ArrayList<Product>();
		Connection con = mySqlDAOFactory.getConnection();
		try(PreparedStatement ps = con.prepareStatement(SELECT_PRODUCTS_BY_CATEGORY);){
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
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
	
	@Override
	public Product getProductById(int id) {
		Product product = null;
		Connection con = mySqlDAOFactory.getConnection();
		try(PreparedStatement ps = con.prepareStatement(SELECT_PRODUCT_BY_ID);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				product = new Product().setId(rs.getInt("ID")).setName(rs.getString("NAME"))
						.setPrice(rs.getInt("PRICE")).setDescription(rs.getString("DESCRIPTION"))
						.setCategory(rs.getInt("CATEGORY"));
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
		return product;
		
		
	}

}
