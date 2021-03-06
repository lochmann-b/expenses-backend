package org.benni.expensesbackend.repositories;

import org.benni.expensesbackend.model.db.ExUser;
import org.springframework.data.repository.CrudRepository;

public interface ExUserRepository extends CrudRepository<ExUser, Integer> {

	ExUser findByLogin(String login);

}
