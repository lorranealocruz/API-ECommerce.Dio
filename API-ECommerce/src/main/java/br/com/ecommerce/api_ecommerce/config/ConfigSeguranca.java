package br.com.ecommerce.api_ecommerce.config;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ecommerce.api_ecommerce.security.JwtAuthenticationFilter;
import br.com.ecommerce.api_ecommerce.security.JwtAuthorizationFilter;
import br.com.ecommerce.api_ecommerce.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		AuthenticationManager authManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
		JwtAuthenticationFilter jwtAuthenticationFilter = 
				new JwtAuthenticationFilter(authManager, jwtUtil);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		JwtAuthorizationFilter jwtAuthorizationFIlter = 
				new JwtAuthorizationFilter(authManager, jwtUtil, userDetailsService);

		http.csrf(csrf -> csrf.disable())
		.headers(headers -> headers.frameOptions().disable())
		.cors((cors) -> cors.configurationSource(corsConfigurationSource()))
		.authorizeHttpRequests(authorize -> 
	        authorize
	        	.requestMatchers("/h2-console/**").permitAll()
	        	.requestMatchers(HttpMethod.POST, "/clientes").permitAll()
	            .requestMatchers(HttpMethod.POST, "/login").permitAll()
	            .requestMatchers(HttpMethod.GET, "/categorias/**", "/produtos/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/categorias", "/produtos", "/transportadoras").permitAll()
	            .requestMatchers(HttpMethod.GET, "/pedidos", "/clientes").permitAll()
	            .requestMatchers(HttpMethod.GET, "/relatorios", "/transportadoras", "/transportadoras/{id}").permitAll()
	            .requestMatchers(HttpMethod.PUT, "/transportadoras/{id}\"").permitAll()
	            .requestMatchers(HttpMethod.DELETE, "/transportadoras/{id}\"").permitAll()
	            
	            .requestMatchers(HttpMethod.PUT, "/pedidos/{id}/status").hasAuthority("ROLE_ADMIN")         
	            .requestMatchers(HttpMethod.GET, "/pedidos/{id}").hasAuthority("ROLE_CLIENTE")
	            
	            .requestMatchers(HttpMethod.POST, "/pedidos").authenticated()
	            .anyRequest().authenticated()
	    )

	    .sessionManagement(session -> 
	        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
	    );
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilter(jwtAuthorizationFIlter);
		
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration corsConfiguration = new CorsConfiguration();
	    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
	    
	    return source; 
	}
	
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
