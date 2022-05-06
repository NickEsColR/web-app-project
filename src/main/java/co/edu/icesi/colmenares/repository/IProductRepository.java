package co.edu.icesi.colmenares.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prod.Product;

@Repository
public interface IProductRepository extends CrudRepository<Product, Integer>{

}
