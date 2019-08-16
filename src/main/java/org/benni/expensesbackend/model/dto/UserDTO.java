package org.benni.expensesbackend.model.dto;

import org.benni.expensesbackend.model.db.ExUser;

public class UserDTO {
	
	private String name;
	private String login;
	private int id;

	public UserDTO(ExUser user) {
		id = user.getId();
		name = user.getName();
		login = user.getLogin();		
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public int getId() {
		return id;
	}
}
