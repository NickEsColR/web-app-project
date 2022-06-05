package co.edu.icesi.colmenares.controller.implementation;

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

import co.edu.icesi.colmenares.controller.interfaces.IBillofmaterialController;
import co.edu.icesi.colmenares.model.prod.Billofmaterial;
import co.edu.icesi.colmenares.service.IBillofmaterialService;
import co.edu.icesi.colmenares.service.IProductService;
import co.edu.icesi.colmenares.service.IUnitmeasureService;

@Controller
public class Billofmaterialcontroller implements IBillofmaterialController {

	@Autowired
	private IBillofmaterialService billofmaterialService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IUnitmeasureService unitmeasureService;
	
	@GetMapping("/billofmaterial/")
	public String index(Model model) {
		model.addAttribute("billofmaterials", billofmaterialService.findAll());
		return "/billofmaterial/index";
	}
	
	@GetMapping("/billofmaterial/add")
	public String showAdd(Model model) {
		model.addAttribute("billofmaterial", new Billofmaterial());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("unitmeasures", unitmeasureService.findAll());
		return "/billofmaterial/add";
	}
	
	@PostMapping("/billofmaterial/add")
	public String add(Model model, @RequestParam(value="action",required=true)String action,
			@Validated @ModelAttribute Billofmaterial b,BindingResult bindingResult,
			@RequestParam(value="unitmeasurev",required=false)Integer uid) {
		if(action.equals("Cancel"))
			return "redirect:/billofmaterial/";
		else if(action.equals("Create new Product")) {
				
			return "redirect:/product/add/billofmaterial";
		}
		else if(action.equals("Create new unit measure"))
			return "redirect:/unitmeasure/add";
		else if(bindingResult.hasErrors()) {
			model.addAttribute("products", productService.findAll());
			model.addAttribute("unitmeasures", unitmeasureService.findAll());
			return "/billofmaterial/add";
		}else
			b.setUnitmeasure(unitmeasureService.findById(uid));
			billofmaterialService.save(b);
		return "redirect:/billofmaterial/";
	}
	
	@GetMapping("/billofmaterial/edit/{id}")
	public String showEdit(Model model, @PathVariable("id")Integer id) {
		Billofmaterial b = billofmaterialService.findById(id);
		if(b == null)
			throw new IllegalArgumentException("Invalid bill of material Id:" + id);
		model.addAttribute("billofmaterial", b);
		model.addAttribute("products", productService.findAll());
		model.addAttribute("unitmeasures", unitmeasureService.findAll());
		return "/billofmaterial/edit";
	}
	
	@PostMapping("/billofmaterial/edit/{id}")
	public String edit(Model model, @RequestParam(value="action",required=true)String action,
			@Validated @ModelAttribute Billofmaterial b, BindingResult bindingResult,
			@RequestParam(value="unitmeasurev",required=false)Integer uid) {
		if(action.equals("Cancel"))
			return "redirect:/billofmaterial/";
		else if(action.equals("Create new Product")) {
		
			return "redirect:/product/add/billofmaterial";
		}
		else if(action.equals("Create new unit measure"))
			return "redirect:/unitmeasure/add";
		else if(bindingResult.hasErrors()) {
			model.addAttribute("products", productService.findAll());
			model.addAttribute("unitmeasures", unitmeasureService.findAll());
			return "/billofmaterial/add";
		}else
			b.setUnitmeasure(unitmeasureService.findById(uid));
			billofmaterialService.save(b);
		return "redirect:/billofmaterial/";
	}
	
	@GetMapping("/billofmaterial/del/{id}")
	public String delete(Model model,@PathVariable("id")Integer id) {
		billofmaterialService.delete(billofmaterialService.findById(id));
		return "redirect:/billofmaterial/";
	}
}
