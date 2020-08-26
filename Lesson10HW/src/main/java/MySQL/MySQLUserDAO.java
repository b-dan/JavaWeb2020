package MySQL;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import service.UserDAO;

public class MySQLUserDAO implements UserDAO{
	
	private MySqlDAOFactory msd;
	private final String SELECT_USER = "SELECT NAME FROM `users` WHERE LOGIN = ? AND PASSWORD = ?;";
	private final String INSERT_INTO_USERS = "INSERT INTO `users` (`ID`, `LOGIN`, `PASSWORD`, `NAME`, `REPASSWORD`, `GENDER`, `ADDRESS`, `COMMENT`, `AGREE`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String SALT = ".dsfe4,";
	
	public MySQLUserDAO(MySqlDAOFactory ms) {
		msd=ms;
	}
	
	@Override
	public User getName(String login, String password) {
		User user = null;
		Connection con = msd.getConnection();
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
		Connection con = msd.getConnection();

		try (PreparedStatement stmt = con.prepareStatement(INSERT_INTO_USERS);) {
			stmt.setString(1, user.getLogin());
			stmt.setString(2, hashPassword(user.getPassword()));
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getGender());
			stmt.setString(5, user.getAddress());
			stmt.setString(6, user.getComment());
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
