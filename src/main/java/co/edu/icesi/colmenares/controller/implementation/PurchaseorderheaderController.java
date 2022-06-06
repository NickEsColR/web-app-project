package co.edu.icesi.colmenares.controller.implementation;

import java.time.LocalDate;
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

import co.edu.icesi.colmenares.businessdelegate.BusinessDelegateRestTemplate;
import co.edu.icesi.colmenares.controller.interfaces.IPurchaseorderheaderController;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.service.IEmployeeService;
import co.edu.icesi.colmenares.service.IPersonService;
import co.edu.icesi.colmenares.service.IPurchaseorderheaderService;
 
@Controller 
public class PurchaseorderheaderController implements IPurchaseorderheaderController {

	@Autowired
	private BusinessDelegateRestTemplate bDelegate;
	
	@GetMapping("/purchaseorderheaders")
	public String loadIndex(Model model) {
		model.addAttribute("purchaseorderheaders",bDelegate.purchaseorderheaderFindAll());
		return "/purchaseorderheaders/index";
	}
	
	@GetMapping("/purchaseorderheaders/add")
	public String loadAdd(Model model) {
		model.addAttribute("purchaseorderheader", new Purchaseorderheader());
		model.addAttribute("employees", bDelegate.employeeFindAll());
		model.addAttribute("people", bDelegate.personFindAll());
		return "/purchaseorderheaders/add";
	}
	
	@PostMapping("/purchaseorderheaders/add")
	public String add(@Validated @ModelAttribute Purchaseorderheader purchaseorderheader, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action
			) {
		if (action.equals("Cancel"))
			return "redirect:/purchaseorderheaders/";
		else if (bindingResult.hasErrors()) {
			model.addAttribute("employees", bDelegate.employeeFindAll());
			model.addAttribute("people", bDelegate.personFindAll());
			return "/purchaseorderheaders/add";
		}else {
			bDelegate.purchaseorderheaderSave(purchaseorderheader);
			return "redirect:/purchaseorderheaders/";
		}
	}
	
	@GetMapping("/purchaseorderheaders/edit/{id}")
	public String loadUpdate(@PathVariable("id")int id, Model model) {
		Purchaseorderheader p = bDelegate.purchaseorderheaderFindById(id);
		if(p == null)
			throw new IllegalArgumentException("Invalid id "+id);
		model.addAttribute("purchaseorderheader", p);
		model.addAttribute("employees", bDelegate.employeeFindAll());
		model.addAttribute("people", bDelegate.personFindAll());
		return "/purchaseorderheaders/edit";
	}
	
	@PostMapping("/purchaseorderheaders/edit/{id}")
	public String update(@PathVariable("id")int id,Model model,
			@Validated @ModelAttribute Purchaseorderheader purchaseorderheader,BindingResult bindingResult,
			@RequestParam(value="action", required=true)String action) {
		if(action.equals("Cancel"))
			return "redirect:/purchaseorderheaders/";
		else if(bindingResult.hasErrors()) {
			model.addAttribute("employees", bDelegate.employeeFindAll());
			model.addAttribute("people", bDelegate.personFindAll());
			return "/purchaseorderheaders/edit";
		}else {
			bDelegate.purchaseorderheaderSave(purchaseorderheader);
		return "redirect:/purchaseorderheaders/";
		}
	}
	
	@GetMapping("/purchaseorderheaders/info/{id}")
	public String showHeaderInfo(@PathVariable("id")int id,Model model) {
		Purchaseorderheader p = bDelegate.purchaseorderheaderFindById(id);
		if(p == null)
			throw new IllegalArgumentException("Invalid id "+id);
		model.addAttribute("purchaseorderheader",p);
		return "/purchaseorderheaders/info";
	}
	
	@GetMapping("/purchaseorderheaders/2+")
	public String findWithTwoplusPurchaseorderdetails(Model model) {
		model.addAttribute("purchaseorderheaders",bDelegate.purchaseorderheaderFindWithTwoplusPurchaseorderdetails());
		return "/purchaseorderheaders/index";
	}
	
	@GetMapping("/purchaseorderheaders/filterDates")
	public String showFilterDate() {
		return "/purchaseorderheaders/filterdate";
	}
	
	@PostMapping("/purchaseorderheaders/")
	public String findAllWithSumUnitprices(Model model, @RequestParam(value="startdate", required=true)String startdate, 
			@RequestParam(value="enddate", required=false)String enddate, 
			@RequestParam(value="action", required=false)String action) {
		if(action.equals("Cancel"))
			return "redirect:/purchaseorderheaders/";
		else {
<<<<<<< HEAD

			
			model.addAttribute("purchaseorderheaders", bDelegate.purchaseorderheaderFindAllWithSumUnitprices
					(LocalDate.parse(startdate), LocalDate.parse(enddate)));

=======
			
			model.addAttribute("purchaseorderheaders", purchaseorderheaderService.findAllWithSumUnitprices
					(LocalDate.parse(startdate), LocalDate.parse(enddate)));
>>>>>>> parent of ec63378 (all views working)
		}
		return "/purchaseorderheaders/index";
	}
}
