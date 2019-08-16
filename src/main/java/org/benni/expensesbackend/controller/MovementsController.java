package org.benni.expensesbackend.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.benni.expensesbackend.model.db.Account;
import org.benni.expensesbackend.model.db.Movement;
import org.benni.expensesbackend.model.dto.MovementDTO;
import org.benni.expensesbackend.services.AccountsServices;
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
public class MovementsController {

	@Autowired
	AccountsServices accountsServices;

	@GetMapping("/api/accounts/{accountId}/movements")
	@ResponseBody
	public Collection<MovementDTO> getMovements(@PathVariable(name = "accountId") int accountId) {
		return accountsServices.findAccountForCurrentUser(accountId).getMovements().stream()
				.map(m -> new MovementDTO(m)).collect(Collectors.toSet());
	}

	@GetMapping("/api/accounts/{accountId}/movements/{movementId}")
	@ResponseBody
	public MovementDTO getMovement(@PathVariable(name = "accountId") int accountId,
			@PathVariable("movementId") int movementId) {
		return new MovementDTO(accountsServices.findMovementForCurrentUser(accountId, movementId));
	}

	@PostMapping("/api/accounts/{accountId}/movements")
	@ResponseBody
	public MovementDTO createMovement(@PathVariable("accountId") int accountId, @RequestBody MovementDTO movementDTO) {
		Account account = accountsServices.findAccountForCurrentUser(accountId);

		Movement movement = new Movement();
		movement.setAccount(account);
		movement.setAmountinCents(movementDTO.getAmountInCents());
		movement.setDate(movementDTO.getDate());
		movement.setDescription(movementDTO.getDescription());

		return new MovementDTO(accountsServices.saveMovement(movement));
	}

	@PutMapping("/api/accounts/{accountId}/movements/{movementId}")
	@ResponseBody
	public MovementDTO updateMovement(@PathVariable("accountId") int accountId,
			@PathVariable("movementId") int movementId, @RequestBody MovementDTO movementDTO) {

		Movement movement = accountsServices.findMovementForCurrentUser(accountId, movementId);
		movement.setAmountinCents(movementDTO.getAmountInCents());
		movement.setDate(movementDTO.getDate());
		movement.setDescription(movementDTO.getDescription());

		return new MovementDTO(accountsServices.saveMovement(movement));
	}

	@DeleteMapping("/api/accounts/{accountId}/movements/{movementId}")
	@ResponseBody
	public ResponseEntity<Integer> deleteMovement(@PathVariable("accountId") int accountId,
			@PathVariable("movementId") int movementId) {
		Movement movement = accountsServices.findMovementForCurrentUser(accountId, movementId);
		accountsServices.deleteMovement(movement);
		return new ResponseEntity<>(movementId, HttpStatus.OK);
	}

}
