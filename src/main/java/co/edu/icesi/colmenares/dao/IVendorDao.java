package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prchasing.Vendor;

public interface IVendorDao {
	
	public void save(Vendor entity);
	public void update(Vendor entity);
	public void delete(Vendor entity);
	public Vendor findById(int id);
	public List<Vendor> findAll();
	public List<Vendor> findByCreditRating(int cr);
}
