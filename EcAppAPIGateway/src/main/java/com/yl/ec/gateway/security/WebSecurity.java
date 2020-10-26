package com.yl.ec.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	private final Environment environment;
	@Autowired
	public WebSecurity(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.cors();
		http.authorizeRequests()
			.antMatchers(environment.getProperty("api.zuul.actuator.url.path")).permitAll()
			.antMatchers(environment.getProperty("api.users.actuator.url.path")).permitAll()
			.antMatchers(HttpMethod.POST, environment.getProperty("api.login.url.path")).permitAll()
			.antMatchers(HttpMethod.POST, environment.getProperty("api.register.url.path")).permitAll()
			.antMatchers(HttpMethod.GET, environment.getProperty("api.services.stores.url.path")).permitAll()
			.antMatchers(HttpMethod.GET, environment.getProperty("api.services.store.url.path")).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(new AuthenticationFilter(authenticationManager(), environment))
			;
		
		// make API stateless, not to create http session, not to cache jwt
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
   
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
