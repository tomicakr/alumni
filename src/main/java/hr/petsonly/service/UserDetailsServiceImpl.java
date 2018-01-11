package hr.petsonly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Role;
import hr.petsonly.model.User;
import hr.petsonly.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Ne postoji korisnik sa e-mailom: " + email);
		}

		boolean enabeled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), enabeled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
	}

	private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
