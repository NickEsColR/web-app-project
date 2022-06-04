package co.edu.icesi.colmenares.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.icesi.colmenares.controller.interfaces.IProductController;
import co.edu.icesi.colmenares.service.IProductService;

@Controller
public class ProductController implements IProductController {

	@Autowired
	private IProductService productService;
}
