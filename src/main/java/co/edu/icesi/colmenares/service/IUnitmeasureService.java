package co.edu.icesi.colmenares.service;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Unitmeasure;

public interface IUnitmeasureService {
	public void save(Unitmeasure u);
	public void delete(Unitmeasure u);
	public Unitmeasure findById(int id);
	public List<Unitmeasure> findAll();
}
