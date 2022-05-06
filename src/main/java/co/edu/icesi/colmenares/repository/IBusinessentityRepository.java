package co.edu.icesi.colmenares.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.person.Businessentity;

@Repository
public interface IBusinessentityRepository extends CrudRepository<Businessentity, Integer>{

}
