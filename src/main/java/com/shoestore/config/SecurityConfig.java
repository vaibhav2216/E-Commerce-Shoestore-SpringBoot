package com.shoestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.shoestore.service.impl.UserSecurityService;
import com.shoestore.utility.SecurityUtility;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private Environment env;

	@Autowired
	private UserSecurityService userSecurityService;

	private BCryptPasswordEncoder passwordEncoder() {

		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = { "/css/**", "/js/**", "/images/**", "/", "/newUser","/forgetPassword",
			"/login",
			"/fonts/**",
			"/shoeshelf",
			"/shoeDetail/**",
			"/searchByCategory",
			"/searchShoe"};

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests().
			/*antMatchers("/**").*/ 
			antMatchers(PUBLIC_MATCHERS).
			permitAll().anyRequest().authenticated();
		http
			.csrf().disable().cors().disable()
			.formLogin().failureUrl("/login?error")
			/* .defaultSuccessUrl("/") */
			.loginPage("/login").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe();
		
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth )throws Exception{
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	//this method allows static resources to be neglected by spring security
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**", "/static/**","/webjars/**");
    }
}
