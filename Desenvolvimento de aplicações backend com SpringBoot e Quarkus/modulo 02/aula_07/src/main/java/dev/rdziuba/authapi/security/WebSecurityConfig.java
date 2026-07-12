package dev.rdziuba.authapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf( (csrf) -> {
			csrf.disable();
		})
		.authorizeHttpRequests((auth) -> {
			auth.requestMatchers(new AntPathRequestMatcher("/open","GET")).permitAll()
			    .requestMatchers(new AntPathRequestMatcher("/users","POST")).permitAll()
			    .requestMatchers(new AntPathRequestMatcher("/login","POST")).permitAll()
			    .anyRequest().authenticated();
		})
		.addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	
	}

}
