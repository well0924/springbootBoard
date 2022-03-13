package web.com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import web.com.spring.login.service.LoginDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	LoginDetailService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().disable()
		.csrf().disable();//기본 로그인 폼 해제
		http
		.authorizeHttpRequests()
		.antMatchers("/login/*")
		.permitAll().anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login/page")
		.successForwardUrl("/board/list")
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true);
	}
	
	@Bean
	public AuthenticationProvider authProvider() throws Exception{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()throws Exception{
		return new BCryptPasswordEncoder();
	}
}
