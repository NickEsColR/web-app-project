package co.edu.icesi.colmenares.service;

import java.util.Optional;

import co.edu.icesi.colmenares.model.security.UserApp;

public interface IUserAppService {
	
	Iterable<UserApp> findAll();
	Optional<UserApp> findById(long id);
	void save(UserApp userApp);
}
