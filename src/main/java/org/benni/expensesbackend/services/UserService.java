package org.benni.expensesbackend.services;

import java.util.Optional;

import org.benni.expensesbackend.controller.exceptions.ResourceNotFoundExceptoin;
import org.benni.expensesbackend.model.db.ExUser;
import org.benni.expensesbackend.repositories.ExUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	ExUserRepository userRepository;

	public ExUser getCurrentUser() {
		String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.ofNullable(userRepository.findByLogin(login)).orElseThrow(() -> new ResourceNotFoundExceptoin());
	}
	
}
