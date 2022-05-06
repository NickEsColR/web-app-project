package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;

public interface IPurchaseorderdetailDao {
	
	public void save(Purchaseorderdetail entity);
	public void update(Purchaseorderdetail entity);
	public void delete(Purchaseorderdetail entity);
	public Purchaseorderdetail findById(int id);
	public List<Purchaseorderdetail> findAll();
}
