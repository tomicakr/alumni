package hr.alumni.config.specific;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import hr.alumni.model.details.CustomUserDetails;

@Component
public class CustomAuthenticationSuccesHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		
		redirectStrategy.sendRedirect(request, response, String.format("/users/%s", user.getUserId()));
	}
	
}
