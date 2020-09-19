package org.fin.dao;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.fin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl implements UserDAO{
	
	private final String SELECT_USER = "SELECT NAME FROM `users` WHERE LOGIN = ? AND PASSWORD = ?;";
	private final String INSERT_INTO_USERS = "INSERT INTO `users` (`ID`, `LOGIN`, `PASSWORD`, `NAME`, `REPASSWORD`, `GENDER`, `ADDRESS`, `COMMENT`, `AGREE`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String SALT = ".dsfe4,";
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	public UserDAOImpl (DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void init () {
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public User getName(String login, String password) {
		User user = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
	@Override
	public void setUser(User user) {
		Connection con=null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try (PreparedStatement stmt = con.prepareStatement(INSERT_INTO_USERS);) {
			stmt.setString(1, user.getLogin());
			stmt.setString(2, hashPassword(user.getPassword()));
			stmt.setString(3, user.getName());
			stmt.setString(4, hashPassword(user.getRePassword()));
			stmt.setString(5, user.getGender());
			stmt.setString(6, user.getAddress());
			stmt.setString(7, user.getComment());
			stmt.setString(8, user.getAgree());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public String hashPassword(String password) throws SQLException {
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

}
