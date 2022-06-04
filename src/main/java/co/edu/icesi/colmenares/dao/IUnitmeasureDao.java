package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Unitmeasure;

public interface IUnitmeasureDao {
	public void save(Unitmeasure u);
	public void delete(Unitmeasure u);
	public Unitmeasure findById(String id);
	public List<Unitmeasure> findAll();
}
