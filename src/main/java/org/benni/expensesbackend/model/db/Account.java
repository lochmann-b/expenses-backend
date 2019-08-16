package org.benni.expensesbackend.model.db;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column
	private String name;
	
	@OneToMany(mappedBy="account")
	private Set<Movement> movements;
	
	@Column
	private int startingBalanceInCents;
	
	@Column
	Date startDate;
	
	@ManyToOne
	private ExUser owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Movement> getMovements() {
		return movements;
	}

	public void setMovements(Set<Movement> movements) {
		this.movements = movements;
	}

	public ExUser getOwner() {
		return owner;
	}

	public void setOwner(ExUser owner) {
		this.owner = owner;
	}

	public int getStartingBalanceInCents() {
		return startingBalanceInCents;
	}

	public void setStartingBalanceInCents(int startingBalanceInCents) {
		this.startingBalanceInCents = startingBalanceInCents;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
}
