package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prod.Productreview;

@Repository
@Scope("singleton")
public class ProductreviewDao implements IProductreviewDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Productreview p) {
		// TODO Auto-generated method stub
		entityManager.merge(p);
	}

	@Override
	public void delete(Productreview p) {
		// TODO Auto-generated method stub
		entityManager.remove(p);
	}

	@Override
	public Productreview findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Productreview.class, id);
	}

	@Override
	public List<Productreview> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select p from Productreview p").getResultList();
	}

}
