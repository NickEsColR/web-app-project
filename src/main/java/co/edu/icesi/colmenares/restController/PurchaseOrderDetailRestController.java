package co.edu.icesi.colmenares.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.service.PurchaseorderdetailService;

@RestController
@RequestMapping("/purchaseorderdetails")
public class PurchaseOrderDetailRestController {
	
	private PurchaseorderdetailService podService;
	
	@Autowired
	public PurchaseOrderDetailRestController( PurchaseorderdetailService pohService) {
		this.podService = pohService;
	}
	
	@PostMapping
	public void add(@RequestBody Purchaseorderdetail pod) {
		podService.savePurchaseorderdetail(pod);
	}
	
	@GetMapping
	public Iterable<Purchaseorderdetail> getAll() {
		return podService.findAll();
	}
	
	@GetMapping("/{id}")
	public Purchaseorderdetail getById(@PathVariable("id") int id) {
		Optional<Purchaseorderdetail> pod = podService.findById(id);
		if(pod.isPresent())
			return pod.get();
		else
			return null;
	}
	
	@PutMapping
	public void update(@RequestBody Purchaseorderdetail pod) {
		podService.setOrderqty(pod.getOrderqty(), pod.getId());
		podService.setUnitprice(pod.getUnitprice(), pod.getId());
	}

	
	
}
