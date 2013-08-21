package com.crud.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.dao.UserDAO;
import com.crud.service.UserService;


@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService,
		Serializable {

	@Autowired
	private UserDAO userDAO;




	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {

		Collection<com.crud.model.User> users = getAllUser();
		System.out.println("SecurityManager.loadUserByUsername()ts"
				+ users.size());
		User springUser = null;
		for (com.crud.model.User user : users) {
			System.out.println("SecurityManager.loadUserByUsername()for"
					+ user.getUsername());
			System.out.println("SecurityManager.loadUserByUsername()pass"
					+ user.getPassword());
			System.out.println("SecurityManager.loadUserByUsername()userName"
					+ userName);
			if (user.getUsername().equals(userName)) {
				ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				GrantedAuthority authority = new GrantedAuthorityImpl(
						user.getRole());
				System.out.println("SecurityManager.loadUserByUsername()"
						+ user.getRole());
				authorities.add(authority);
				springUser = new User(user.getUsername(), user.getPassword(),
						true, true, true, true, authorities);
				System.out.println("SecurityManager.loadUserByUsername()"
						+ springUser.getUsername());
				return springUser;
			}
		}
		throw new UsernameNotFoundException("User not found.");
	}

	private Collection<com.crud.model.User> getAllUser() {

		return userDAO.getAllUsers();
	}



	@Override
	public List<com.crud.model.User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.getAllUsers();

	}

	@Override
	public void createUser(com.crud.model.User user) {
		userDAO.createUser(user);
		
	}

	@Override
	public void updateUser(com.crud.model.User user) {
		userDAO.updateUser(user);
		
	}

	@Override
	public void deleteUser(List<com.crud.model.User> users) {
		
		userDAO.deleteUser(users);
		
	}

}
