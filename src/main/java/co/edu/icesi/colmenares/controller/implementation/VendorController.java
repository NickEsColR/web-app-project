package co.edu.icesi.colmenares.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.colmenares.controller.interfaces.IVendorController;
import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.service.IBusinessentityService;
import co.edu.icesi.colmenares.service.IVendorService;

@Controller
public class VendorController implements IVendorController {

	@Autowired
	private IVendorService vendorService;
	
	@Autowired
	private IBusinessentityService businessentityService;

	@GetMapping("/vendors/")
	public String loadIndex(Model model) {
		model.addAttribute("vendors", vendorService.findAll());
		return "/vendors/index";
	}

	@GetMapping("/vendors/add")
	public String loadAdd(Model model) {
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("businessentities", businessentityService.findAll());
		return "/vendors/add-vendor";
	}

	@PostMapping("/vendors/add")
	public String addVendor(@Validated @ModelAttribute Vendor vendor, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action
			) {
		if (action.equals("Cancel"))
			return "redirect:/vendors/";
		else if(action.equals("Create new Business entity")) {
			businessentityService.save(new Businessentity());
			model.addAttribute("businessentities", businessentityService.findAll());
			return "/vendors/add-vendor";
		}
		else if (bindingResult.hasErrors()) {
			model.addAttribute("businessentities", businessentityService.findAll());
			return "/vendors/add-vendor";
		}else {
			vendorService.saveVendor(vendor);			
			return "redirect:/vendors/";
		}
	}
	
	@GetMapping("/vendors/edit/{id}")
	public String loadEdit(@PathVariable("id") Integer id, Model model) {
		Optional<Vendor> v = vendorService.findById(id);
		if(v.isEmpty())
			throw new IllegalArgumentException("Invalid vendor Id:" + id);
		model.addAttribute("vendor", v.get());
		model.addAttribute("businessentities", businessentityService.findAll());
		return "/vendors/edit";
	}
	
	@PostMapping("/vendors/edit/{id}")
	public String update(@PathVariable("id") Integer id, Model model, 
			@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Vendor vendor, BindingResult bindingResult) {
		if(action.equals("Cancel")) {
			return "redirect:/vendors/";	
		}else if(action.equals("Create new Business entity")) {
			businessentityService.save(new Businessentity());
			model.addAttribute("businessentities", businessentityService.findAll());
			return "/vendors/edit";
		}else if(bindingResult.hasErrors()) {
			model.addAttribute("businessentities", businessentityService.findAll());
			return "/vendors/edit";
		}else {
			vendorService.saveVendor(vendor);
			return "redirect:/vendors/";
		}
	}
}
