package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prod.Billofmaterial;

@Repository
@Scope("singleton")
public class BillofmaterialDao implements IBillofmaterialDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Billofmaterial b) {
		// TODO Auto-generated method stub
		entityManager.merge(b);
	}

	@Override
	public void delete(Billofmaterial b) {
		// TODO Auto-generated method stub
		entityManager.remove(b);
	}

	@Override
	public Billofmaterial findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Billofmaterial.class, id);
	}

	@Override
	public List<Billofmaterial> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select b from Billofmaterial b").getResultList();
	}

}
