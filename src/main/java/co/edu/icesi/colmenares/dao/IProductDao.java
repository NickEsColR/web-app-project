package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Product;

public interface IProductDao {
	public void save(Product p);
	public Product findById(int id);
	public void delete(Product p);
	public List<Product> findAll();
}
