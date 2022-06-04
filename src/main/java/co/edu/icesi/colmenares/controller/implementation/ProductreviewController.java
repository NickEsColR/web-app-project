package co.edu.icesi.colmenares.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.icesi.colmenares.controller.interfaces.IProductreviewController;
import co.edu.icesi.colmenares.service.IProductreviewService;

@Controller
public class ProductreviewController implements IProductreviewController {

	@Autowired
	private IProductreviewService productreviewService;
}
