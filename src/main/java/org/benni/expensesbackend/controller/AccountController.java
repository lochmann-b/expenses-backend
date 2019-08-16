package org.benni.expensesbackend.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.benni.expensesbackend.model.db.Account;
import org.benni.expensesbackend.model.db.ExUser;
import org.benni.expensesbackend.model.dto.AccountDTO;
import org.benni.expensesbackend.services.AccountsServices;
import org.benni.expensesbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	@Autowired
	private AccountsServices accountsService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("api/accounts/{accountId}")
	@ResponseBody
	public AccountDTO getAccounts(@PathVariable(value="accountId") int accountId) {
		return new AccountDTO(accountsService.findAccountForCurrentUser(accountId));
	}
	
	@GetMapping("api/accounts")
	@ResponseBody
	public Collection<AccountDTO> getAccounts() {
		ExUser exUser = userService.getCurrentUser();
		return exUser.getAccounts().stream().map(a -> new AccountDTO(a)).collect(Collectors.toSet());
	}
	
	@DeleteMapping("api/accounts/{accountId}")
	public ResponseEntity<Integer> eleteAccount(@PathVariable(name="accountId")int accountId) {
		Account account = accountsService.findAccountForCurrentUser(accountId);
		accountsService.delete(account);		
		return new ResponseEntity<>(accountId, HttpStatus.OK);
	}
	
	@PutMapping("api/accounts/{accountId}")
	@ResponseBody
	public AccountDTO updateAccount(@PathVariable(name="accountId")int accountId, @RequestBody AccountDTO accountDTO) {
		Account account = accountsService.findAccountForCurrentUser(accountId);
		
		account.setName(accountDTO.getName());		
		account.setStartDate(accountDTO.getStartDate());
		account.setStartingBalanceInCents(accountDTO.getStartingBalanceInCents());
		
		accountsService.save(account);
		return new AccountDTO(account);
	}

	@PostMapping("api/accounts")
	@ResponseBody
	public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
		ExUser user = userService.getCurrentUser();
		Account account = new Account();
		
		account.setName(accountDTO.getName());
		account.setOwner(user);
		account.setStartDate(accountDTO.getStartDate());
		account.setStartingBalanceInCents(accountDTO.getStartingBalanceInCents());
		
		accountsService.save(account);
		return new AccountDTO(account);
	}
	
}
