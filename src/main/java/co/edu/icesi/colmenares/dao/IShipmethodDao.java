package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prchasing.Shipmethod;

public interface IShipmethodDao {
	
	public void save(Shipmethod entity);
	public void update(Shipmethod entity);
	public void delete(Shipmethod entity);
	public Shipmethod findById(int id);
	public List<Shipmethod> findAll();
}
