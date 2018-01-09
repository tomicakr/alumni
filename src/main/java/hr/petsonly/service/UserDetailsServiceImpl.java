//package hr.petsonly.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import hr.petsonly.repository.UserRepository;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String emailAsUsername) throws UsernameNotFoundException {
//		UserDetails user = userRepository.findByEmail(emailAsUsername);
//
//		if (user == null) {
//			throw new UsernameNotFoundException("Ne postoji korisnik sa e-mailom: " + emailAsUsername);
//		}
//
//		return user;
//	}
//
//}
