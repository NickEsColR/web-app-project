package co.edu.icesi.colmenares.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;

public interface IPurchaseorderheaderService {
	
	void savePurchaseorderheader(Purchaseorderheader value);
	void setSubtotal(BigDecimal value, int id);
	void setOrderdate(LocalDate value, int id);
	void setEmployeeid(int Employeeid, int id);
	void setPersonid(int personid, int id);
	BigDecimal getSubtotal(int id);
	LocalDate getOrderdate(int id);
	int getEmployeeid(int id);
	int getPersonid(int id);
	Iterable<Purchaseorderheader> findAll();
	Optional<Purchaseorderheader> findById(int id);
	List<Purchaseorderheader> findWithTwoplusPurchaseorderdetails();
	List<Purchaseorderheader> findAllWithSumUnitprices(LocalDate start, LocalDate end);
}
