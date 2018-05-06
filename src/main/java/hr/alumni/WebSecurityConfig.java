package hr.alumni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hr.alumni.config.specific.CustomAuthenticationFailureHandler;
import hr.alumni.config.specific.CustomAuthenticationSuccesHandler;
import hr.alumni.model.details.CustomUserDetails;

import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final CustomAuthenticationSuccesHandler sucessHandler;
	private final CustomAuthenticationFailureHandler failureHandler;

	@Autowired
	public WebSecurityConfig(UserDetailsService userDetailsService, CustomAuthenticationSuccesHandler sucessHandler, CustomAuthenticationFailureHandler failureHandler) {
		this.userDetailsService = userDetailsService;
		this.sucessHandler = sucessHandler;
		this.failureHandler = failureHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage("/sessions/new")
				.loginProcessingUrl("/sessions/")
				.failureHandler(failureHandler)
				.successHandler(sucessHandler);
		
		http.csrf().disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	public boolean checkUserId(Authentication auth, UUID id) {
		Object o = auth.getPrincipal();
		if (!(o instanceof CustomUserDetails))
			return false;

		CustomUserDetails user = (CustomUserDetails) o;

		if (user.getRoles().contains("ROLE_ADMINISTRATOR"))
			return true;
		if (!user.getUserId().equals(id))
			return false;

		return true;
	}

}