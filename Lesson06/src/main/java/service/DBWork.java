package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import userModel.User;

public class DBWork {
	private final String SELECT_USER = "SELECT NAME FROM `users` WHERE LOGIN = ? AND PASSWORD = ?;";
	private final String INSERT_INTO_USERS = "INSERT INTO `users` (`ID`, `LOGIN`, `PASSWORD`, `NAME`, `REPASSWORD`, `GENDER`, `ADDRESS`, `COMMENT`, `AGREE`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";
	public DBWork() {
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
	public User getName(String login, String password) {
		User user = null;
		Connection con = getConnection();
		try(PreparedStatement ps = con.prepareStatement(SELECT_USER);){
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setName(rs.getString("NAME"));
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
		return user;

	}
	public void setUser(String login, String password, String name, String rePassword, String gender, String address, String comment, String agree) {
		PreparedStatement ps = null;
		Connection con = getConnection();
		try{ps = con.prepareStatement(INSERT_INTO_USERS);
			ps.setString(1, login);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, rePassword);
			ps.setString(5, gender);
			ps.setString(6, address);
			ps.setString(7, comment);
			ps.setString(8, agree);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		DBWork dbwork = new DBWork();
	}
}
