package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Billofmaterial;

public interface IBillofmaterialDao {
	public void save(Billofmaterial b);
	public void delete(Billofmaterial b);
	public Billofmaterial findById(int id);
	public List<Billofmaterial> findAll();
}
