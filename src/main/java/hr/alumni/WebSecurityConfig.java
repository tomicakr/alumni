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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import hr.alumni.config.specific.CustomAuthenticationFailureHandler;
import hr.alumni.config.specific.CustomAuthenticationSuccesHandler;
import hr.alumni.config.specific.CustomLogoutSuccessHandler;
import hr.alumni.model.details.CustomUserDetails;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final CustomAuthenticationSuccesHandler sucessHandler;
	private final CustomAuthenticationFailureHandler failureHandler;
	private final CustomLogoutSuccessHandler logoutHandler;

	@Autowired
	public WebSecurityConfig(UserDetailsService userDetailsService, CustomAuthenticationSuccesHandler sucessHandler, CustomAuthenticationFailureHandler failureHandler, CustomLogoutSuccessHandler logoutHandler) {
		this.userDetailsService = userDetailsService;
		this.sucessHandler = sucessHandler;
		this.failureHandler = failureHandler;
		this.logoutHandler = logoutHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.failureHandler(failureHandler)
				.successHandler(sucessHandler);

		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutHandler);
		
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