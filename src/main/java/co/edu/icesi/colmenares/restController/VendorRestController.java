package co.edu.icesi.colmenares.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.service.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorRestController {

	private VendorService vendorService;
	
	@Autowired
	public VendorRestController(VendorService vendorService) {
		this.vendorService = vendorService;
	}
	
	@PostMapping
	public void add(@RequestBody Vendor vendor) {
		vendorService.saveVendor(vendor);
	}
	
	@GetMapping
	public Iterable<Vendor> getAll() {
		return vendorService.findAll();
	}
	
	@GetMapping("/{id}")
	public Vendor getById(@PathVariable("id") int id) {
		Optional<Vendor> vendor = vendorService.findById(id);
		if(vendor.isPresent())
			return vendor.get();
		else
			return null;
	}
}
