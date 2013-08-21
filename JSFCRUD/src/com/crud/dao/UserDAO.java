package com.crud.dao;

import java.util.List;

import com.crud.model.User;

public interface UserDAO {

	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(List<User> users);

	public List<User> getAllUsers();

	
	
}
