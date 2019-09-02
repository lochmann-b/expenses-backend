package org.benni.expensesbackend.model.dto;

import java.sql.Date;
import java.util.Optional;

import org.benni.expensesbackend.model.db.Movement;

public class MovementDTO implements Comparable<MovementDTO>{

	private int accountId;
	private int amountInCents;
	private Date date;
	private String description;
	private int id;
	
	public MovementDTO() {
		
	}

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

	public void setAmountInCents(int amountInCents) {
		this.amountInCents = amountInCents;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(MovementDTO o) {
		int cp = o.getDate().compareTo(getDate());
		if (cp == 0) {
			cp = Optional.ofNullable(getDescription()).orElse("").compareTo(Optional.ofNullable(o.getDescription()).orElse(""));
		}
		if (cp == 0) {
			return getId() - o.getId();
		}
		return cp;
	}
	
	
	
	

}
