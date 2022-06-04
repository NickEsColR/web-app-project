package co.edu.icesi.colmenares.service;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Productreview;

public interface IProductreviewService {
	public void save(Productreview p);
	public void delete(Productreview p);
	public Productreview findById(int id);
	public List<Productreview> findAll();
}
