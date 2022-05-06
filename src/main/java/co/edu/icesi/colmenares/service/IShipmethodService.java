package co.edu.icesi.colmenares.service;

import java.math.BigDecimal;
import java.util.Optional;

import co.edu.icesi.colmenares.model.prchasing.Shipmethod;

public interface IShipmethodService {
	
	void saveShipmethod(Shipmethod value);
	void setShipbase(BigDecimal value, int id);
	void setShiprate(BigDecimal value, int id);
	void setName(String value, int id);
	BigDecimal getShipbase(int id);
	BigDecimal getShiprate(int id);
	String getName( int id);
	Iterable<Shipmethod> findAll();
	Optional<Shipmethod> findById(Integer id);
}
