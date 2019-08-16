package org.benni.expensesbackend.services;

import org.benni.expensesbackend.controller.exceptions.ResourceNotFoundExceptoin;
import org.benni.expensesbackend.model.db.Account;
import org.benni.expensesbackend.model.db.ExUser;
import org.benni.expensesbackend.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServices {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountRepository accountsRepository;
	
	public Account findAccountForCurrentUser(int accountId) {
		ExUser exUser = userService.getCurrentUser();
		return exUser.getAccounts().stream().filter(a -> a.getId()== accountId).findAny().orElseThrow(() -> new ResourceNotFoundExceptoin());
	}

	public void save(Account account) {
		accountsRepository.save(account);
		
	}

	public void delete(Account account) {
		accountsRepository.delete(account);	
	}
	
}
