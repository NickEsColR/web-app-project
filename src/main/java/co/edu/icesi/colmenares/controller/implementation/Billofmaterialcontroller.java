package co.edu.icesi.colmenares.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.icesi.colmenares.controller.interfaces.IBillofmaterialController;
import co.edu.icesi.colmenares.service.IBillofmaterialService;

@Controller
public class Billofmaterialcontroller implements IBillofmaterialController {

	@Autowired
	private IBillofmaterialService billofmaterialService;
}
