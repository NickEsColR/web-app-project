package co.edu.icesi.colmenares.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.security.UserApp;

@Repository
public interface IUserAppRepository extends CrudRepository<UserApp, Long> {
	
	Optional<UserApp> findByUsername(String username);
}
