package co.edu.icesi.colmenares.service;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Billofmaterial;

public interface IBillofmaterialService {
	public void save(Billofmaterial b);
	public void delete(Billofmaterial b);
	public Billofmaterial findById(int id);
	public List<Billofmaterial> findAll();
}
