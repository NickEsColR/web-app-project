package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;

@Repository
@Scope("singleton")
public class PurchaseorderheaderDao implements IPurchaseorderheaderDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Purchaseorderheader entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(Purchaseorderheader entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(Purchaseorderheader entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Purchaseorderheader findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Purchaseorderheader.class, id);
	}

	@Override
	public List<Purchaseorderheader> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select p from Purchaseorderheader p").getResultList();
	}

}
