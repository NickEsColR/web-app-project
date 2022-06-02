package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prod.Productreview;

public interface IProductreviewDao {
	public void save(Productreview p);
	public void delete(Productreview p);
	public Productreview findById(int id);
	public List<Productreview> findAll();
}
