package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import service.DAOFactory;
import service.ProductDAO;
import service.UserDAO;

public class MySqlDAOFactory extends DAOFactory{
	
	public MySqlDAOFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	@Override
	public UserDAO getUserDAO() {
		return new MySQLUserDAO(this);
	}
	@Override
	public ProductDAO getProductDAO() {
		return new MySQLProductDAO(this);
	}

}
