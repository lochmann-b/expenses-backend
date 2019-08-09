package org.benni.expensesbackend.controller;

import java.util.Map;

import org.benni.expensesbackend.model.Account;
import org.benni.expensesbackend.model.ExUser;
import org.benni.expensesbackend.repositories.AccountRepository;
import org.benni.expensesbackend.repositories.ExUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
	
	@Autowired
	ExUserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;

	@PostMapping("api/accounts")
	public int createAccount(@RequestBody Map<String, Object> obj) {
		String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ExUser exUser = userRepository.findByLogin(login);
		Account account = new Account();
		account.setName((String) obj.get("name"));
		account.setOwner(exUser);		
		accountRepository.save(account);
		return account.getId();
	}
	
}
