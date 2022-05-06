package co.edu.icesi.colmenares.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Shipmethod;

@Repository
public interface IShipmethodRepository extends CrudRepository<Shipmethod, Integer>{

}
