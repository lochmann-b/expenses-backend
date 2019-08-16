package org.benni.expensesbackend.repositories;

import org.benni.expensesbackend.model.db.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
