package com.crud.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.crud.model.User;

public class UserDataModel extends ListDataModel<User> implements
		SelectableDataModel<User>, Serializable {

	public UserDataModel(List<User> data) {
		super(data);
	}

	@Override
	public User getRowData(String rowKey) {
		// TODO Auto-generated method stub
		List<User> users = (List<User>) getWrappedData();

		for (User user : users) {
			if (user.getId() == Integer.parseInt(rowKey))
				return user;
		}

		return null;
	}

	@Override
	public Object getRowKey(User user) {
		// TODO Auto-generated method stub
		return user.getId();
	}

}
