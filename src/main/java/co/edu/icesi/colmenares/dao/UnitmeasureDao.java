package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prod.Unitmeasure;

@Repository
@Scope("singleton")
public class UnitmeasureDao implements IUnitmeasureDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Unitmeasure u) {
		// TODO Auto-generated method stub
		entityManager.merge(u);
	}

	@Override
	public void delete(Unitmeasure u) {
		// TODO Auto-generated method stub
		entityManager.remove(u);
	}

	@Override
	public Unitmeasure findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Unitmeasure.class, id);
	}

	@Override
	public List<Unitmeasure> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select u from Unitmeasure u").getResultList();
	}

}
