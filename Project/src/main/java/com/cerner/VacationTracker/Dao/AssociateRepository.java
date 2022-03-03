package com.cerner.VacationTracker.Dao;
import org.springframework.data.repository.CrudRepository;

/**
 * AssociateRepository provides a CRUD interface for Associate entity
 * @author AS078223
 */
import org.springframework.stereotype.Repository;

import com.cerner.VacationTracker.Entity.Associate;
@Repository
public interface AssociateRepository extends CrudRepository<Associate,String>{
	
}
