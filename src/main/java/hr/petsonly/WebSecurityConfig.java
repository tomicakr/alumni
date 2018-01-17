package hr.petsonly;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import hr.petsonly.config.specific.CustomAuthenticationSuccesHandler;
import hr.petsonly.model.details.CustomUserDetails;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationSuccesHandler sucessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/sessions/new").loginProcessingUrl("/sessions/").failureUrl("/sessions/new?error=1").successHandler(sucessHandler);
		
		
		http.authorizeRequests().antMatchers("/users").hasRole("ADMINISTRATOR").antMatchers("/jobs").hasAnyRole("ZAPOSLENIK", "ADMINISTRATOR");
		
		http.authorizeRequests().antMatchers("/users/{id}").access("@webSecurityConfig.checkUserId(authentication, #id)");
		
		http.csrf().disable();
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService);
	}
	
	public boolean checkUserId(Authentication auth, UUID id) {
		Object o = auth.getPrincipal();
		if (!(o instanceof CustomUserDetails)) return false;
		
		CustomUserDetails user = (CustomUserDetails) o;
		
		if(user.getRoles().contains("ROLE_ADMINISTRATOR")) return true;
		if(!user.getUserId().equals(id)) return false;
		
		return true;
	}

}