package co.edu.icesi.colmenares.service;

import java.util.List;
import java.util.Optional;

import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.prchasing.Vendor;

public interface IVendorService {
	public void saveVendor(Vendor vendor);
	public void setCreditrating(int value,int id);
	public void setPurchasingwebserviceurl(String value, int id);
	public void setName(String value, int id);
	public void setBusinessentityId(int value, int id);
	public Vendor getVendor(int id);
	public int getCreditrating(int id);
	public String getPurchasingwebserviceurl(int id);
	public String getName(int id);
	public int getBusinessentityId(int id);
	public Iterable<Vendor> findAll();
	Optional<Vendor> findById(Integer id);
}