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

import co.edu.icesi.colmenares.controller.interfaces.IPurchaseorderheaderController;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.service.IEmployeeService;
import co.edu.icesi.colmenares.service.IPersonService;
import co.edu.icesi.colmenares.service.IPurchaseorderheaderService;
 
@Controller 
public class PurchaseorderheaderController implements IPurchaseorderheaderController {

	@Autowired
	private IPurchaseorderheaderService purchaseorderheaderService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IPersonService personService;
	
	@GetMapping("/purchaseorderheaders")
	public String loadIndex(Model model) {
		model.addAttribute("purchaseorderheaders",purchaseorderheaderService.findAll());
		return "/purchaseorderheaders/index";
	}
	
	@GetMapping("/purchaseorderheaders/add")
	public String loadAdd(Model model) {
		model.addAttribute("purchaseorderheader", new Purchaseorderheader());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("people", personService.findAll());
		return "/purchaseorderheaders/add";
	}
	
	@PostMapping("/purchaseorderheaders/add")
	public String add(@Validated @ModelAttribute Purchaseorderheader purchaseorderheader, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action
			) {
		if (action.equals("Cancel"))
			return "redirect:/purchaseorderheaders/";
		else if (bindingResult.hasErrors()) {
			model.addAttribute("employees", employeeService.findAll());
			model.addAttribute("people", personService.findAll());
			return "/purchaseorderheaders/add";
		}else {
			purchaseorderheaderService.savePurchaseorderheader(purchaseorderheader);
			return "redirect:/purchaseorderheaders/";
		}
	}
	
	@GetMapping("/purchaseorderheaders/edit/{id}")
	public String loadUpdate(@PathVariable("id")int id, Model model) {
		Optional<Purchaseorderheader> p = purchaseorderheaderService.findById(id);
		if(p.isEmpty())
			throw new IllegalArgumentException("Invalid id "+id);
		model.addAttribute("purchaseorderheader", p.get());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("people", personService.findAll());
		return "/purchaseorderheaders/edit";
	}
	
	@PostMapping("/purchaseorderheaders/edit/{id}")
	public String update(@PathVariable("id")int id,Model model,
			@Validated @ModelAttribute Purchaseorderheader purchaseorderheader,BindingResult bindingResult,
			@RequestParam(value="action", required=true)String action) {
		if(action.equals("Cancel"))
			return "redirect:/purchaseorderheaders/";
		else if(bindingResult.hasErrors()) {
			model.addAttribute("employees", employeeService.findAll());
			model.addAttribute("people", personService.findAll());
			return "/purchaseorderheaders/edit";
		}else {
		purchaseorderheaderService.savePurchaseorderheader(purchaseorderheader);
		return "redirect:/purchaseorderheaders/";
		}
	}
	
	@GetMapping("/purchaseorderheaders/info/{id}")
	public String showHeaderInfo(@PathVariable("id")int id,Model model) {
		Optional<Purchaseorderheader> p = purchaseorderheaderService.findById(id);
		if(p.isEmpty())
			throw new IllegalArgumentException("Invalid id "+id);
		model.addAttribute("purchaseorderheader",p.get());
		return "/purchaseorderheaders/info";
	}
}