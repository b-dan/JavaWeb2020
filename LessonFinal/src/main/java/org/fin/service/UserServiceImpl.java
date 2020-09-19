package org.fin.service;

import java.sql.SQLException;

import org.fin.dao.UserDAO;
import org.fin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDAO;

	@Override
	public User getName(String login, String password) {
		return userDAO.getName(login, password);
	}

	@Override
	public void setUser(User user) {
		userDAO.setUser(user);
	}

	@Override
	public String hashPassword(String password) throws SQLException {
		return hashPassword(password);
	}

}
