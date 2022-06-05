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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.colmenares.controller.interfaces.IUnitmeasureController;
import co.edu.icesi.colmenares.model.prod.Unitmeasure;
import co.edu.icesi.colmenares.service.IUnitmeasureService;

@Controller
public class UnitmeasureController implements IUnitmeasureController {

	@Autowired
	private IUnitmeasureService unitmeasureService;
	
	
	@GetMapping("/unitmeasure/add")
	public String showadd(Model model) {
		model.addAttribute("unitmeasure",new Unitmeasure());
		return "/unitmeasure/add";
	}
	
	@PostMapping("/unitmeasure/add")
	public String add(Model model, @RequestParam(value="action",required=true)String action,
			@Validated @ModelAttribute Unitmeasure u, BindingResult bindingResult) {
		if(action.equals("Cancel"))
			return "redirect:/billofmaterial/";
		else if(bindingResult.hasErrors())
			return "/unitmeasure/add";
		unitmeasureService.save(u);
		return "redirect:/billofmaterial/";
	}
	
	@GetMapping("/unitmeasure/info/{id}")
	public String info(Model model, @PathVariable("id")Integer id) {
		Unitmeasure u = unitmeasureService.findById(id);
		if(u==null)
			throw new IllegalArgumentException("Invalid unit measure Id:" + id);
		model.addAttribute("unitmeasure", u);
		return "/unitmeasure/info";
	}
	
	@PostMapping("/unitmeasure/info/{id}")
	public String exitinfo(Model model, @RequestParam(value="action",required=true)String action) {
		return "redirect:/billofmaterial/";
	}
}
