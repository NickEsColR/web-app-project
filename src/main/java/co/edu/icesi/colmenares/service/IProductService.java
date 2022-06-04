package co.edu.icesi.colmenares.service;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Product;

public interface IProductService {
	public void save(Product p);
	public void delete(Product p);
	public Product findById(int id);
	public List<Product> findAll();
}
