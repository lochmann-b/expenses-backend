package org.benni.expensesbackend.repositories;

import org.benni.expensesbackend.model.db.Movement;
import org.springframework.data.repository.CrudRepository;

public interface MovementRepository extends CrudRepository<Movement, Integer> {

}
