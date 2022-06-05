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

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.service.PurchaseorderheaderService;

@RestController
@RequestMapping("/api/purchaseorderheaders")
public class PurchaseOrderHeaderRestController {
	
	private PurchaseorderheaderService pohService;
	
	@Autowired
	public PurchaseOrderHeaderRestController(PurchaseorderheaderService pohService) {
		this.pohService = pohService;
				
	}
	
	@PostMapping
	public void add(@RequestBody Purchaseorderheader poh) {
		pohService.savePurchaseorderheader(poh);
	}
	
	@GetMapping
	public Iterable<Purchaseorderheader> getAll() {
		return pohService.findAll();
	}
	
	@GetMapping("/{id}")
	public Purchaseorderheader getById(@PathVariable("id") int id) {
		Optional<Purchaseorderheader> poh = pohService.findById(id);
		if(poh.isPresent())
			return poh.get();
		else
			return null;
	}
	
	@PutMapping
	public void update(@RequestBody Purchaseorderheader poh) {
		pohService.setOrderdate(poh.getOrderdate(), poh.getPurchaseorderid());
		pohService.setEmployeeid(poh.getEmployeeid(), poh.getPurchaseorderid());
		pohService.setPersonid(poh.getPersonid(), poh.getPurchaseorderid());
		pohService.setSubtotal(poh.getSubtotal(), poh.getPurchaseorderid());

	}
	

}
