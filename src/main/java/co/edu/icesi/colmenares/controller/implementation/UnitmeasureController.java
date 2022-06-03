package co.edu.icesi.colmenares.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.icesi.colmenares.controller.interfaces.IUnitmeasureController;
import co.edu.icesi.colmenares.service.IUnitmeasureService;

@Controller
public class UnitmeasureController implements IUnitmeasureController {

	@Autowired
	private IUnitmeasureService unitmeasureService;
}
