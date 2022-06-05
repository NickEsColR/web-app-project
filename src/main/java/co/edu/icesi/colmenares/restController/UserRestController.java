package co.edu.icesi.colmenares.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.service.UserAppService;



@RestController
@RequestMapping("/api/users")
public class UserRestController {
	private UserAppService userService;

	@Autowired
	public UserRestController(UserAppService userService) {
		this.userService = userService;
	}

	@PostMapping
	public void add(@RequestBody UserApp user) {
		userService.save(user);
	}


	@GetMapping
	public Iterable<UserApp> getAll() {
		return userService.findAll();
	}


	@GetMapping("/{id}")
	public UserApp getById(@PathVariable("id") long id) {
		Optional<UserApp> oUser = userService.findById(id);
		if(oUser.isPresent())
			return oUser.get();
		else
			return null;
	}

}

