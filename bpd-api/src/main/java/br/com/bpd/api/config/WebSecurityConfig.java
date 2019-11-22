package br.com.bpd.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.bpd.common.constants.PathsApiServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("#{systemProperties['environment']}")
	private String environment;

	@Value("#{systemProperties['bpd.client.https.url']}")
	private String bpdHttpsClient;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("myUser").password("{noop}myPassword").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
		.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.PUT, PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY).hasRole("USER")
				.antMatchers(HttpMethod.GET,
						PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORIES,
						PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY + PathsApiServices.ROOT + PathsApiServices.ID
				).hasRole("USER")
				.antMatchers(HttpMethod.POST, PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY).hasRole("USER")
				.antMatchers(HttpMethod.DELETE, PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY + PathsApiServices.ROOT + PathsApiServices.ID).hasRole("USER")
		.and()
			.csrf()
				.disable()
			.formLogin()
				.disable();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		List<String> origins = ( environment != null && "PROD".equals(environment) ) ? Arrays.asList(bpdHttpsClient) : Arrays.asList("*");
		
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(origins);
		configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "PUT", "POST", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
