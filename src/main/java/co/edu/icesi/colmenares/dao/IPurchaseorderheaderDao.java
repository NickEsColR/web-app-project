package co.edu.icesi.colmenares.dao;

import java.util.List;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;

public interface IPurchaseorderheaderDao {
	
	public void save(Purchaseorderheader entity);
	public void update(Purchaseorderheader entity);
	public void delete(Purchaseorderheader entity);
	public Purchaseorderheader findById(int id);
	public List<Purchaseorderheader> findAll();
	public List<Purchaseorderheader> findByShipmethod(Shipmethod s);
	public List<Purchaseorderheader> findByVendor(Vendor v);
	//usando jpql
	public List<Purchaseorderheader> findWithTwoplusPurchaseorderdetails();//2.b
	//devolver el encabezado y la suma de los unitprice de sus details
	public List<Purchaseorderheader> findAllWithSumUnitprices();
}
