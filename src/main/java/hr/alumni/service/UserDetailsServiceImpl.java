package hr.alumni.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.alumni.model.Privilege;
import hr.alumni.model.Role;
import hr.alumni.model.User;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.repository.UserRepository;

@Service
@Transactional
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

		List<Role> userRoles = user.getRoles();

		return new CustomUserDetails(user.getEmail(), user.getPassword(), enabeled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, getAuthorities(userRoles), user.getFirstName(),
				user.getUserId(), user.getRoles().stream().map(new Function<Role, String>() {

					@Override
					public String apply(Role t) {
						return t.getName();
					}
				}).collect(Collectors.toList()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
			privileges.add(role.getName());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
