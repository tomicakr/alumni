package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.petsonly.model.User;
import com.petsonly.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailAsUsername) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(emailAsUsername);
		if(user == null) {
			return null;
		}
		return null;
	}

}
