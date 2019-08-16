package org.benni.expensesbackend.model.dto;

import java.sql.Date;

import org.benni.expensesbackend.model.db.Movement;

public class MovementDTO {

	private int accountId;
	private int amountInCents;
	private Date date;
	private String description;
	private int id;

	public MovementDTO(Movement m) {
		id = m.getId();
		accountId = m.getAccount().getId();
		amountInCents = m.getAmountinCents();
		date = m.getDate();
		description = m.getDescription();		
	}

	public int getAccountId() {
		return accountId;
	}

	public int getAmountInCents() {
		return amountInCents;
	}

	public Date getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}
	
	

}
