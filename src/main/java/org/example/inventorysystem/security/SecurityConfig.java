package org.example.inventorysystem.security;

import org.example.inventorysystem.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.userDetailsService = userDetailsServiceImpl;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	@Configuration
	public class WebConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// Pozwól na dostęp do wszystkich ścieżek z dowolnej domeny
			registry.addMapping("/**") // Ustawiasz do jakich endpointów CORS ma być dostępny
					.allowedOrigins("http://localhost:4200") // Możesz dodać inne domeny
					.allowedMethods("GET", "POST", "PUT", "DELETE") // Ustawić metody HTTP, które są dozwolone
					.allowedHeaders("*") // Pozwól na wszystkie nagłówki
					.allowCredentials(true); // Jeśli masz wymaganie co do przesyłania ciasteczek / tokenów
		}
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(auth -> auth
						.requestMatchers("/auth/login").permitAll()
						.anyRequest().authenticated()
				)
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}
}