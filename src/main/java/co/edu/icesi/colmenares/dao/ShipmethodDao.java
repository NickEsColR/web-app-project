package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Shipmethod;

@Repository
@Scope("singleton")
public class ShipmethodDao implements IShipmethodDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Shipmethod entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(Shipmethod entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(Shipmethod entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Shipmethod findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Shipmethod.class, id);
	}

	@Override
	public List<Shipmethod> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select s from Shipmethod s").getResultList();
	}

}
