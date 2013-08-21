package com.crud.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

import com.crud.model.User;
import com.crud.service.UserService;
import com.crud.service.impl.UserServiceImpl;

@ManagedBean
@ViewScoped
@Controller
public class UserMB  {

	@Autowired
	private UserDetailsService userDetailsService;

	public void setUserDetailsService(UserServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public UserDataModel userDataModel;

	public User user;

	public List<User> userList;

	public User selectedUser;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public List<User> selectedUsers;

	@PostConstruct
	public void init() {

		userList = getAllUsers();

		userDataModel = new UserDataModel(userList);

	}

	public UserMB() {

		user = new User();

		selectedUser = new User();

		userList = new ArrayList<User>();

		selectedUsers = new ArrayList<User>();
	}

	public UserDataModel getUserDataModel() {
		return userDataModel;
	}

	private void updateTable() {
		userList = new ArrayList();
		userDataModel.setWrappedData(null);
		userList = getAllUsers();
		userDataModel.setWrappedData(userList);

	}

	public void setUserDataModel(UserDataModel userDataModel) {
		this.userDataModel = userDataModel;
	}


	private void resetForm() {
		user.setUsername("");
		user.setPassword("");
		user.setRole("");
	}

	public void onRowSelect(SelectEvent selectEvent) {

		selectedUser = (User) selectEvent.getObject();

	}

	public void onRowUnselect(UnselectEvent event) {

	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub

		System.out.println("UserMB.getAllUsers()" + userDetailsService);
		return ((UserService) userDetailsService).getAllUsers();
	}

	public void addUser(ActionEvent actionEvent) {
		((UserService) userDetailsService).createUser(user);
		updateTable();
		resetForm();
	}

	public void updateUser(ActionEvent actionEvent) {
		((UserService) userDetailsService).updateUser(selectedUser);
		updateTable();
	}

	public void deleteUser(ActionEvent actionEvent) {
		((UserService) userDetailsService).deleteUser(selectedUsers);
		updateTable();

	}

	

}
