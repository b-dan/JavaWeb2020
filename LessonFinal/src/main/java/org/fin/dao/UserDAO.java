package org.fin.dao;

import java.sql.SQLException;

import org.fin.model.User;

public interface UserDAO {
	User getName(String login, String password);
	void setUser(User user);
	String hashPassword(String password) throws SQLException;

}
