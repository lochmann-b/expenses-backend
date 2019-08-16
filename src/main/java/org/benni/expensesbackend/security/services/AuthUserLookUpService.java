package org.benni.expensesbackend.security.services;

import org.benni.expensesbackend.model.db.ExUser;
import org.benni.expensesbackend.repositories.ExUserRepository;
import org.benni.expensesbackend.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserLookUpService {

	@Autowired
	ExUserRepository userRepository;
	
	User findUser(String username) {
		ExUser exUser = userRepository.findByLogin(username);
		if(exUser != null) {
			return new User(exUser.getLogin(), exUser.getPassword());
		}
		return null;
	}

}