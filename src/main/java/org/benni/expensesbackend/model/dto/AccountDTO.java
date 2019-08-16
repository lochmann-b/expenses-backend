package org.benni.expensesbackend.model.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.benni.expensesbackend.model.db.Account;

public class AccountDTO {

	private String name;
	private int startingBalanceInCents;
	private Date startDate;
	private Set<MovementDTO> movements;
	private int id;

	public AccountDTO() {
		
	}
	
	public AccountDTO(Account a) {
		id = a.getId();
		name = a.getName();
		startingBalanceInCents = a.getStartingBalanceInCents();
		startDate = a.getStartDate();
		movements = Optional.ofNullable(a.getMovements()).orElse(new HashSet<>()).stream().map(m -> new MovementDTO(m))
				.collect(Collectors.toSet());
	}

	public String getName() {
		return name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Set<MovementDTO> getMovements() {
		return movements;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartingBalanceInCents() {
		return startingBalanceInCents;
	}

	public void setStartingBalanceInCents(int startingBalanceInCents) {
		this.startingBalanceInCents = startingBalanceInCents;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
