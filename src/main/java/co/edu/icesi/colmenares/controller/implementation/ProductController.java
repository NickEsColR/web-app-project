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

import co.edu.icesi.colmenares.controller.interfaces.IProductController;
import co.edu.icesi.colmenares.model.prod.Product;
import co.edu.icesi.colmenares.service.IProductService;

@Controller
public class ProductController implements IProductController {

	@Autowired
	private IProductService productService;
	
	@GetMapping("/product/add/{from}")
	public String showadd(Model model,@PathVariable("from")String from) {
		model.addAttribute("product", new Product());
		model.addAttribute("from", from);
		return "/product/add";
	}
	
	@PostMapping("/product/add/{from}")
	public String add(Model model, @RequestParam(value="action",required=true)String action,
			@Validated @ModelAttribute Product product, BindingResult bindingResult,
			@PathVariable("from")String from) {
		if(action.equals("Cancel")) {
			if(from.equals("productreview"))
				return "redirect:/productreview/";
			return "redirect:/billofmaterial/";
		}
		else if(bindingResult.hasErrors())
			return "/product/add";
		else {
			productService.save(product);
			if(from.equals("productreview"))
				return "redirect:/productreview/";
			return "redirect:/billofmaterial/";
		}
	}
	
	@GetMapping("/product/info/{from}/{id}")
	public String info(Model model, @PathVariable("id")Integer id,@PathVariable("from")String from) {
		Product p = productService.findById(id);
		if(p == null)
			throw new IllegalArgumentException("Invalid Product Id:" + id);
		model.addAttribute("product", p);
		return "/product/info";
	}
	
	@GetMapping("/product/info/{from}")
	public String infoexit(Model model, @RequestParam(value="action",required=true)String action,
			@PathVariable("from")String from) {
		System.out.println(from);
		if(from.equals("productreview"))
			return "redirect:/productreview/";
		return "redirect:/billofmaterial/";
	}
}
