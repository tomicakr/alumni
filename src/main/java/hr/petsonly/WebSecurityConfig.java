package hr.petsonly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import hr.petsonly.config.specific.CustomAuthenticationSuccesHandler;

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

}