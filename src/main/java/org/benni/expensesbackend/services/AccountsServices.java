package org.benni.expensesbackend.services;

import org.benni.expensesbackend.controller.exceptions.ResourceNotFoundExceptoin;
import org.benni.expensesbackend.model.db.Account;
import org.benni.expensesbackend.model.db.ExUser;
import org.benni.expensesbackend.model.db.Movement;
import org.benni.expensesbackend.repositories.AccountRepository;
import org.benni.expensesbackend.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServices {

	@Autowired
	UserService userService;

	@Autowired
	AccountRepository accountsRepository;

	@Autowired
	MovementRepository movementRepository;

	public Account findAccountForCurrentUser(int accountId) {
		ExUser exUser = userService.getCurrentUser();
		return exUser.getAccounts().stream().filter(a -> a.getId() == accountId).findAny()
				.orElseThrow(ResourceNotFoundExceptoin::new);
	}

	public Account saveAccount(Account account) {
		return accountsRepository.save(account);
	}

	public void deleteAccount(Account account) {
		accountsRepository.delete(account);
	}

	public Movement findMovementForCurrentUser(int accountId, int movementId) {
		return findAccountForCurrentUser(accountId).getMovements().stream().filter(m -> m.getId() == movementId)
				.findAny().orElseThrow(ResourceNotFoundExceptoin::new);
	}

	public Movement saveMovement(Movement movement) {
		return movementRepository.save(movement);
	}

	public void deleteMovement(Movement movement) {
		movementRepository.delete(movement);
	}

}
