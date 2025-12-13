package in.aap.main.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import in.aap.main.filters.JWTFilter;
import in.aap.main.service.CustomerDertailsService;

@Configuration
@EnableWebSecurity
public class Config {
	
	@Autowired
	private CustomerDertailsService customerdetails;
	@Autowired
	private JWTFilter filter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http
				.csrf(customizer -> customizer.disable())
				.cors(cutomizer -> cutomizer.configurationSource(corsconfig()))
				.formLogin(customizer -> customizer.disable())
				.httpBasic(Customizer.withDefaults())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/auth/**")
						.permitAll()
						.anyRequest()
						.authenticated()
						)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider provider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customerdetails);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
	
	@Bean
	public CorsConfigurationSource corsconfig() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("https://127.0.0.1:5174"));
		config.setAllowedHeaders(List.of("Authentication","Content-Type"));
		config.setAllowedMethods(List.of("PUT","DELETE","GET","POST","OPTIONS"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
}
