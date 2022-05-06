package co.edu.icesi.colmenares.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.repository.IUserAppRepository;

@Service
public class UserAppService implements IUserAppService {
	
	@Autowired
	private IUserAppRepository userAppRepository;
	
	@Override
	public Iterable<UserApp> findAll() {
		// TODO Auto-generated method stub
		return userAppRepository.findAll();
	}

	@Override
	public Optional<UserApp> findById(long id) {
		// TODO Auto-generated method stub
		return userAppRepository.findById(id);
	}

	@Override
	public void save(UserApp userApp) {
		// TODO Auto-generated method stub
		userAppRepository.save(userApp);
	}

}
