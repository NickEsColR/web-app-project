package co.edu.icesi.colmenares.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.colmenares.dao.IPurchaseorderdetailDao;
import co.edu.icesi.colmenares.dao.IPurchaseorderheaderDao;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.repository.IPurchaseorderdetailRepository;
import co.edu.icesi.colmenares.repository.IPurchaseorderheaderRepository;

@Service
public class PurchaseorderdetailService implements IPurchaseorderdetailService {
	
	private IPurchaseorderdetailDao purchaseorderdetailRepository;
	private IPurchaseorderheaderDao purchaseorderheaderRepossitory;
	
	
	public PurchaseorderdetailService(IPurchaseorderdetailDao purchaseorderdetailRepository,
			IPurchaseorderheaderDao purchaseorderheaderRepossitory) {
		this.purchaseorderdetailRepository = purchaseorderdetailRepository;
		this.purchaseorderheaderRepossitory = purchaseorderheaderRepossitory;
	}


	@Override
	@Transactional
	public void savePurchaseorderdetail(Purchaseorderdetail value) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepossitory.findById(value.getPurchaseorderheader().getPurchaseorderid());
		if(value.getOrderqty()<=0 || value.getUnitprice().intValue()<=0 || poh.isEmpty()) {
			throw new IllegalArgumentException();
		}
		value.setPurchaseorderheader(poh.get());
		purchaseorderdetailRepository.save(value);
	}


	@Override
	@Transactional
	public void setOrderqty(int value, java.lang.Integer id) {
		// TODO Auto-generated method stub
		if(value<=0) {
			throw new IllegalArgumentException();
		}
		Optional<Purchaseorderdetail> pod = purchaseorderdetailRepository.findById(id);
		if(pod.isPresent()) {
			pod.get().setOrderqty(value);
		}
	}


	@Override
	@Transactional
	public void setUnitprice(BigDecimal value, java.lang.Integer id) {
		// TODO Auto-generated method stub
		if(value.intValue() <=0) {
			throw new IllegalArgumentException();
		}
		Optional<Purchaseorderdetail> pod = purchaseorderdetailRepository.findById(id);
		if(pod.isPresent()) {
			pod.get().setUnitprice(value);
		}
	}


	@Override
	@Transactional
	public void setPurchaseorderheader(Purchaseorderheader value, java.lang.Integer id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderdetail> pod = purchaseorderdetailRepository.findById(id);
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepossitory.findById(value.getPurchaseorderid());
		if(poh.isEmpty()) {
			throw new IllegalArgumentException();
		}else if(pod.isPresent()) {
			pod.get().setPurchaseorderheader(value);
		}
	}


	@Override
	public int getOrderqty(java.lang.Integer id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderdetail> pod = purchaseorderdetailRepository.findById(id);
		if(pod.isPresent()) {
			return pod.get().getOrderqty();
		}
		return 0;
	}


	@Override
	public BigDecimal getUnitprice(java.lang.Integer id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderdetail> pod = purchaseorderdetailRepository.findById(id);
		if(pod.isPresent()) {
			return pod.get().getUnitprice();
		}
		return null;
	}


	@Override
	public Purchaseorderheader getPurchaseorderheaderid(java.lang.Integer id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderdetail> pod = purchaseorderdetailRepository.findById(id);
		if(pod.isPresent()) {
			return pod.get().getPurchaseorderheader();
		}
		return null;
	}


	@Override
	public Iterable<Purchaseorderdetail> findAll() {
		// TODO Auto-generated method stub
		return purchaseorderdetailRepository.findAll();
	}


	@Override
	public Optional<Purchaseorderdetail> findById(Integer id) {
		// TODO Auto-generated method stub
		return purchaseorderdetailRepository.findById(id);
	}


}
