package co.edu.icesi.colmenares.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.model.security.UserType;
import co.edu.icesi.colmenares.repository.IUserAppRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService{
	@Autowired
	private IUserAppRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserApp> userApp = userRepository.findByUsername(username);
		if (userApp.isPresent()) {
			User.UserBuilder builder = User.withUsername(username).password(userApp.get().getPassword()).roles(userApp.get().getType());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}
