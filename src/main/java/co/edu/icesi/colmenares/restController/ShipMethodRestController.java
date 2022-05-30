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

import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.service.ShipmethodService;

@RestController
@RequestMapping("/shipmethods")
public class ShipMethodRestController {
	
	private ShipmethodService smService;
	
	@Autowired
	public ShipMethodRestController(ShipmethodService smService) {
		this.smService = smService;
	}
	
	@PostMapping
	public void add(@RequestBody Shipmethod shipmethod) {
		smService.saveShipmethod(shipmethod);
	}
	
	@GetMapping
	public Iterable<Shipmethod> getAll() {
		return smService.findAll();
	}
	
	@GetMapping("/{id}")
	public Shipmethod getById(@PathVariable("id") int id) {
		Optional<Shipmethod> shipmethod = smService.findById(id);
		if(shipmethod.isPresent())
			return shipmethod.get();
		else
			return null;
	}
	
	@PutMapping
	public void update(@RequestBody Shipmethod shipmethod) {
		smService.setName(shipmethod.getName(), shipmethod.getShipmethodid());
		smService.setShipbase(shipmethod.getShipbase(), shipmethod.getShipmethodid());
		smService.setShiprate(shipmethod.getShiprate(), shipmethod.getShipmethodid());

	}

}
