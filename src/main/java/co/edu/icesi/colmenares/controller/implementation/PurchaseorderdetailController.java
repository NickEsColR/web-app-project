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

import co.edu.icesi.colmenares.businessdelegate.BusinessDelegateRestTemplate;
import co.edu.icesi.colmenares.controller.interfaces.IPurchaseorderdetailController;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.service.IPurchaseorderdetailService;
import co.edu.icesi.colmenares.service.IPurchaseorderheaderService;

@Controller
public class PurchaseorderdetailController implements IPurchaseorderdetailController {

	@Autowired
	private BusinessDelegateRestTemplate bDelegate;
	
	@GetMapping("/purchaseorderdetails")
	public String loadIndex(Model model) {
		model.addAttribute("purchaseorderdetails",bDelegate.purchaseorderdetailFindAll());
		return "/purchaseorderdetails/index";
	}
	
	@GetMapping("/purchaseorderdetails/add")
	public String loadAdd(Model model) {
		model.addAttribute("purchaseorderdetail", new Purchaseorderdetail());
		model.addAttribute("purchaseorderheaders", bDelegate.purchaseorderheaderFindAll());
		return "/purchaseorderdetails/add";
	}
	
	@PostMapping("/purchaseorderdetails/add")
	public String add(@Validated @ModelAttribute Purchaseorderdetail purchaseorderdetail, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action
			) {
		if (action.equals("Cancel"))
			return "redirect:/purchaseorderdetails/";
		else if (bindingResult.hasErrors()) {
			model.addAttribute("purchaseorderheaders",bDelegate.purchaseorderheaderFindAll());
			return "/purchaseorderdetails/add";
		}else {
			bDelegate.purchaseorderdetailSave(purchaseorderdetail);
			return "redirect:/purchaseorderdetails/";
		}
	}
	
	@GetMapping("/purchaseorderdetails/edit/{id}")
	public String loadUpdate(@PathVariable("id")Integer id,Model model) {
		System.out.println("carga edit");
		Purchaseorderdetail p = bDelegate.purchaseorderdetailFindById(id);
		if(p == null)
			throw new IllegalArgumentException("Invalid id "+id);
		model.addAttribute("purchaseorderdetail", p);
		model.addAttribute("purchaseorderheaders",bDelegate.purchaseorderheaderFindAll());
		System.out.println("fin carga edit");
		return "/purchaseorderdetails/edit";
	}
	
	@PostMapping("/purchaseorderdetails/edit/{id}")
	public String update(@PathVariable("id")Integer id,Model model,
			@Validated @ModelAttribute Purchaseorderdetail purchaseorderdetail, BindingResult bindigResult,
			@RequestParam(value="action", required=true)String action) {
		if(action.equals("Cancel"))
			return "redirect:/purchaseorderdetails/";
		else if(bindigResult.hasErrors()) {
			model.addAttribute("purchaseorderheaders", bDelegate.purchaseorderheaderFindAll());
			return "purchaseorderdetails/edit";
		}else {
			bDelegate.purchaseorderdetailSave(purchaseorderdetail);
			return "redirect:/purchaseorderdetails/";
		}
	}
	
}
