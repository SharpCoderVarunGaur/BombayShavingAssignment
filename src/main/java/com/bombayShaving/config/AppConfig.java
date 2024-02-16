package com.bombayShaving.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.bombayShaving.security.JwtAuthenticationEntryPoint;
import com.bombayShaving.security.JwtFilter;



@Configuration
public class AppConfig {
	@Autowired
	private  JwtFilter jwtFilter;
	
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint entrypoint;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return daoAuthenticationProvider;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.
		csrf(csrf->csrf.disable()).cors(c->c.disable()).authorizeHttpRequests(auth->auth.requestMatchers("api/auth/login").permitAll().requestMatchers("/Sign-up/**").permitAll().requestMatchers("Role/**").permitAll().anyRequest().authenticated())
		.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.exceptionHandling(e->e.authenticationEntryPoint(entrypoint))
		;
		
		http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		http.authenticationProvider(authenticationProvider());
	      DefaultSecurityFilterChain chain = http.build();
	      return chain;
	}

}
