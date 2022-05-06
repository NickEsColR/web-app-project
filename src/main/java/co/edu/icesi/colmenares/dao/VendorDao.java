package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Vendor;

@Repository
@Scope("singleton")
public class VendorDao implements IVendorDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Vendor entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(Vendor entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(Vendor entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Vendor findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Vendor.class, id);
	}

	@Override
	public List<Vendor> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select v from Vendor v").getResultList();
	}

}
