package co.edu.icesi.colmenares.service;

import java.math.BigDecimal;
import java.util.Optional;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;

public interface IPurchaseorderdetailService {
	
	void savePurchaseorderdetail(Purchaseorderdetail value);
	void setOrderqty(int value,java.lang.Integer id);
	void setUnitprice(BigDecimal value, java.lang.Integer id);
	void setPurchaseorderheader(Purchaseorderheader value, java.lang.Integer id);
	int getOrderqty(java.lang.Integer id);
	BigDecimal getUnitprice(java.lang.Integer id);
	Purchaseorderheader getPurchaseorderheaderid(java.lang.Integer id);
	Iterable<Purchaseorderdetail> findAll();
	Optional<Purchaseorderdetail> findById(Integer id);
}
