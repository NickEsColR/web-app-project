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

import co.edu.icesi.colmenares.controller.interfaces.IShipmethodController;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.service.IShipmethodService;

@Controller
public class ShipmethodController implements IShipmethodController {

	@Autowired
	private IShipmethodService shipmethodService;

	@GetMapping("/shipmethods/")
	public String loadIndex(Model model) {
		model.addAttribute("shipmethods", shipmethodService.findAll());
		return "/shipmethods/index";
	}
	
	@GetMapping("/shipmethods/add")
	public String loadAdd(Model model) {
		model.addAttribute("shipmethod", new Shipmethod());
		return "/shipmethods/add";
	}
	
	@PostMapping("/shipmethods/add")
	public String add(@Validated @ModelAttribute Shipmethod shipmethod, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action
			) {
		if (action.equals("Cancel"))
			return "redirect:/shipmethods/";
		else if (bindingResult.hasErrors()) {
			return "/shipmethods/add";
		}else {
			shipmethodService.saveShipmethod(shipmethod);			
			return "redirect:/shipmethods/";
		}
	}
	
	@GetMapping("/shipmethods/edit/{id}")
	public String loadEdit(Model model, @PathVariable("id")Integer id) {
		Optional<Shipmethod> s = shipmethodService.findById(id);
		if(s.isEmpty()) 
			throw new IllegalArgumentException("invalid id "+id);
		model.addAttribute("shipmethod", s.get());	
		return "/shipmethods/edit";
	}
	
	@PostMapping("/shipmethods/edit/{id}")
	public String update(Model model, @PathVariable("id")Integer id, 
			@Validated @ModelAttribute Shipmethod shipmethod, BindingResult bindingResult, 
			@RequestParam(value="action", required =true)String action) {
		if(action.equals("Cancel"))
			return "redirect:/shipmethods/";
		else if(bindingResult.hasErrors())
			return "/shipmethods/edit";
		else
			shipmethodService.saveShipmethod(shipmethod);
		return "redirect:/shipmethods/";
	}
}
