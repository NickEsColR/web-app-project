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

import co.edu.icesi.colmenares.controller.interfaces.IProductreviewController;
import co.edu.icesi.colmenares.model.prod.Productreview;
import co.edu.icesi.colmenares.service.IProductService;
import co.edu.icesi.colmenares.service.IProductreviewService;

@Controller
public class ProductreviewController implements IProductreviewController {

	@Autowired
	private IProductreviewService productreviewService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/productreview/")
	public String index(Model model) {
		model.addAttribute("productreviews", productreviewService.findAll());
		return "/productreview/index";
	}
	
	@GetMapping("/productreview/add")
	public String showAdd(Model model) {
		model.addAttribute("productreview",new Productreview());
		model.addAttribute("products", productService.findAll());
		return "/productreview/add";
	}
	
	@PostMapping("/productreview/add")
	public String add(Model model, @RequestParam(value="action",required=true)String action,
			@Validated @ModelAttribute Productreview p,BindingResult bindingResult) {
		if(action.equals("Cancel"))
			return "redirect:/productreview/";
		else if(action.equals("Create new Product")) {
			
			return "redirect:/product/add/productreview";
		}
		else if(bindingResult.hasErrors()) {
			model.addAttribute("products", productService.findAll());
			return "/productreview/add";
		}else
			productreviewService.save(p);
		return "redirect:/productreview/";
	}
	
	@GetMapping("/productreview/edit/{id}")
	public String showEdit(Model model, @PathVariable("id")Integer id) {
		Productreview p = productreviewService.findById(id);
		if(p==null)
			throw new IllegalArgumentException("Invalid product review Id:" + id);
		model.addAttribute("productreview", p);
		model.addAttribute("products", productService.findAll());
		return "/productreview/edit";
	}
	
	@PostMapping("/productreview/edit/{id}")
	public String edit(Model model,@RequestParam(value="action",required=true)String action,
			@Validated @ModelAttribute Productreview p,BindingResult bindingResult) {
		if(action.equals("Cancel"))
			return "redirect:/productreview/";
		else if(action.equals("Create new Product")) {
			return "redirect:/product/add/productreview";
		}
		else if(bindingResult.hasErrors()) {
			model.addAttribute("products", productService.findAll());
			return "/productreview/add";
		}else
			productreviewService.save(p);
		return "redirect:/productreview/";
	}
	
	@GetMapping("/productreview/del/{id}")
	public String delete(Model model, @PathVariable("id")Integer id) {
		productreviewService.delete(productreviewService.findById(id));
		return "redirect:/productreview/";
	}
}
