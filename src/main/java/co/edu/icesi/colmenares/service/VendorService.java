package co.edu.icesi.colmenares.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.colmenares.dao.IVendorDao;
import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.repository.IBusinessentityRepository;
import co.edu.icesi.colmenares.repository.IVendorRepository;

@Service
public class VendorService implements IVendorService {
	

	private IBusinessentityRepository businessRepository;
	private IVendorDao vendorRepository;
	
	@Autowired
	public VendorService(IBusinessentityRepository businessRepository, IVendorDao vendorRepository) {
		this.businessRepository = businessRepository;
		this.vendorRepository = vendorRepository;
	}
	@Override
	@Transactional
	public void saveVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		Optional<Businessentity> b = businessRepository.findById(vendor.getBusinessentityid());
		if(vendor.getCreditrating() <= 0 || !vendor.getPurchasingwebserviceurl().startsWith("https") || vendor.getName() == null
				|| b.isEmpty()) {
			throw new IllegalArgumentException();
		}
		vendorRepository.save(vendor);
	}

	@Override
	@Transactional
	public void setCreditrating(int value,int id) {
		// TODO Auto-generated method stub
		if(value <= 0) {
			throw new IllegalArgumentException();
		}
		Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
		if(v.isPresent()) {
			v.get().setCreditrating(value);
		}
	}
	@Override
	@Transactional
	public void setPurchasingwebserviceurl(String value, int id) {
		// TODO Auto-generated method stub
		if(value.startsWith("https")){
			Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
			if(v.isPresent()) {
				v.get().setPurchasingwebserviceurl(value);
			}
		}else {
			throw new IllegalArgumentException();
		}
		
	}
	@Override
	@Transactional
	public void setName(String value, int id) {
		// TODO Auto-generated method stub
		if(value == null) {
			throw new IllegalArgumentException();
		}
		Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
		if(v.isPresent()) {
			v.get().setName(value);
		}
	}
	@Override
	@Transactional
	public void setBusinessentityId(int value, int id) {
		// TODO Auto-generated method stub
		Optional<Vendor> v =  Optional.ofNullable(vendorRepository.findById(id));
		Optional<Businessentity> b = businessRepository.findById(value);
		if(b.isEmpty()) {
			throw new IllegalArgumentException();
		} else if(v.isPresent()) {
			v.get().setBusinessentityid(value);
		}
	}
	@Override
	public Vendor getVendor(int id) {
		// TODO Auto-generated method stub
		Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
		if(v.isPresent()) {
			return v.get();
		}
		return null;
	}
	@Override
	public int getCreditrating(int id) {
		// TODO Auto-generated method stub
		Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
		if(v.isPresent()) {
			return v.get().getCreditrating();
		}
		return 0;
	}
	@Override
	public String getPurchasingwebserviceurl(int id) {
		// TODO Auto-generated method stub
		Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
		if(v.isPresent()) {
			return v.get().getPurchasingwebserviceurl();
		}
		return null;
	}
	@Override
	public String getName(int id) {
		// TODO Auto-generated method stub
		Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
		if(v.isPresent()) {
			return v.get().getName();
		}
		return null;
	}
	@Override
	public int getBusinessentityId(int id) {
		// TODO Auto-generated method stub
		Optional<Vendor> v = Optional.ofNullable(vendorRepository.findById(id));
		if(v.isPresent()) {
			return v.get().getBusinessentityid();
		}
		return 0;
	}
	@Override
	public Iterable<Vendor> findAll() {
		// TODO Auto-generated method stub
		return vendorRepository.findAll();
	}
	@Override
	public Optional<Vendor> findById(Integer id) {
		// TODO Auto-generated method stub
		return  Optional.ofNullable(vendorRepository.findById(id));
	}

	

}
