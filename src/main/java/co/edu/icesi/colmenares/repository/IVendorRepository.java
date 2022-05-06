package co.edu.icesi.colmenares.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Vendor;

@Repository
public interface IVendorRepository extends CrudRepository<Vendor, Integer> {

}
