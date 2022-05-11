package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;

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

	@Override
	public List<Purchaseorderheader> findByShipmethod(Shipmethod s) {
		// TODO Auto-generated method stub
		String sql ="select p from Purchaseorderheader p where shipmethod="+s.getShipmethodid();
		return entityManager.createQuery(sql).getResultList();
	}

	@Override
	public List<Purchaseorderheader> findByVendor(Vendor v) {
		// TODO Auto-generated method stub
		String sql = "select p from Purchaseorderheader p where vendor="+v.getVendorid();
		return entityManager.createQuery(sql).getResultList();
	}

	@Override
	public List<Purchaseorderheader> findWithTwoplusPurchaseorderdetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Purchaseorderheader> findAllWithSumUnitprices() {
		// TODO Auto-generated method stub
		return null;
	}

}
