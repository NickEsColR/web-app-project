package co.edu.icesi.colmenares.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;

@Repository
public interface IPurchaseorderheaderRepository extends CrudRepository<Purchaseorderheader, Integer> {

}
