package co.edu.icesi.colmenares.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
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
		entityManager.merge(entity);
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
		String sql ="select p from Purchaseorderheader p where shipmethod.id="+s.getShipmethodid();
		return entityManager.createQuery(sql).getResultList();
	}

	@Override
	public List<Purchaseorderheader> findByVendor(Vendor v) {
		// TODO Auto-generated method stub
		String sql = "select p from Purchaseorderheader p where vendor.id="+v.getVendorid();
		return entityManager.createQuery(sql).getResultList();
	}

	@Override
	public List<Purchaseorderheader> findWithTwoplusPurchaseorderdetails() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Purchaseorderheader> query = cb.createQuery(Purchaseorderheader.class);
		Root<Purchaseorderheader> poh = query.from(Purchaseorderheader.class);
		Path<Purchaseorderdetail> pod = poh.get("purchaseorderdetails");
//		List<Predicate> predicates = new ArrayList<>();
		//contar la lista purchaseorderdetails > 2 en where se puede?

		query.select(poh).where(cb.gt(cb.count(pod), 2));			
		
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<Purchaseorderheader> findAllWithSumUnitprices() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Purchaseorderheader> query = cb.createQuery(Purchaseorderheader.class);
		Root<Purchaseorderheader> poh = query.from(Purchaseorderheader.class);
		Path<BigDecimal> unitPrice = query.from(Purchaseorderdetail.class).get("unitprice");
		Path<Purchaseorderheader> pohOfpod = query.from(Purchaseorderdetail.class).get("purchaseorderheader");
		//select poh, sum(pod.unitprice) where equals(pod.poh,poh)x
		query.multiselect(poh,cb.sum(unitPrice)).where(cb.equal(poh, pohOfpod));
		
		return entityManager.createQuery(query).getResultList();
	}

}
