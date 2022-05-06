package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;

public interface IPurchaseorderheaderDao {
	
	public void save(Purchaseorderheader entity);
	public void update(Purchaseorderheader entity);
	public void delete(Purchaseorderheader entity);
	public Purchaseorderheader findById(int id);
	public List<Purchaseorderheader> findAll();
}
