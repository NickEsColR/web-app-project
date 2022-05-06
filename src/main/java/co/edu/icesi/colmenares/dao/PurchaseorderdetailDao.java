package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;

@Repository
@Scope("singleton")
public class PurchaseorderdetailDao implements IPurchaseorderdetailDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Purchaseorderdetail entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(Purchaseorderdetail entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(Purchaseorderdetail entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Purchaseorderdetail findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Purchaseorderdetail.class, id);
	}

	@Override
	public List<Purchaseorderdetail> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select p from Purchaseorderdetail p").getResultList();
	}

}