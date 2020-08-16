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

import model.User;

public class DBWork{
	private final String SELECT_USER = "SELECT NAME FROM `users` WHERE LOGIN = ? AND PASSWORD = ?;";
	private final String INSERT_INTO_USERS = "INSERT INTO `users` (`ID`, `LOGIN`, `PASSWORD`, `NAME`, `REPASSWORD`, `GENDER`, `ADDRESS`, `COMMENT`, `AGREE`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String SALT = ".dsfe4,";
	private SQLConnection connection;
	
	
	public DBWork() {
		connection = new SQLConnection();
	}
	public User getName(String login, String password) {
		User user = null;
		Connection con = connection.getConnection();
		try(PreparedStatement ps = con.prepareStatement(SELECT_USER);){
			ps.setString(1, login);
			ps.setString(2, hashPassword(password));
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
		Connection con = connection.getConnection();
		try{ps = con.prepareStatement(INSERT_INTO_USERS);
			ps.setString(1, login);
			ps.setString(2, hashPassword(password));
			ps.setString(3, name);
			ps.setString(4, hashPassword(rePassword));
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
	
	private String hashPassword(String password) throws SQLException {
		String hashPass = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(StandardCharsets.UTF_8.encode(password+SALT));
			hashPass = String.format("%032x", new BigInteger(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			throw new SQLException();
		}
		return hashPass;
	}
	public static void main(String[] args) {
		DBWork dbwork = new DBWork();
	}
}
