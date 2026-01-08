package in.aap.main.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.aap.main.user.service.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService service;
	private UsersService detailsservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String header = request.getHeader("Authorization");
			String username = "";
			String token = "";
			if (header != null && header.contains("Bearer ")) {
				token = header.substring(7);
				username = service.extractUsername(token);
				if (username != null && !username.isBlank()
						&& SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails details = detailsservice.loadUserByUsername(username);
					if (service.validate(token, details)) {
						UsernamePasswordAuthenticationToken uptoken = new UsernamePasswordAuthenticationToken(details,
								null, details.getAuthorities());
						uptoken.setDetails(details);
						SecurityContextHolder.getContext().setAuthentication(uptoken);
					} else {
						// logger ayega
					}
				} else {
					// logger ayega
				}
			} else {
				// logger ayega
			}
		} finally {
			filterChain.doFilter(request, response);
		}
	}

}
