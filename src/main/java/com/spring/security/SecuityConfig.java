package com.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // if we have this annotation , directly at controller calss we can
													// use at methods @PreAuthorize("hasRole('ADMIN')")
public class SecuityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable() // for POST and PUT method to work
				.authorizeRequests() // we should authorize the request
				.antMatchers("/public/**").permitAll() // if the url is "/public/**" then give permission to any person.
				.antMatchers("/custody/**").hasRole("ADMIN").antMatchers("/signin").permitAll().anyRequest() // any
																												// request
																												// we
																												// should
																												// authorized
				.authenticated() // then authenticate
				.and() // and then
				.formLogin().loginPage("/signin") // for form based authentication so when you login , you get a form.
													// CTRL shift i , youll get that code of the drop down form . use
													// the same parameters from it with your outer design and make a
													// login page for you
				.loginProcessingUrl("/dologin").defaultSuccessUrl("/public/home");
		
		// here its form authorization .
		// we couldhvae used basic http auth too
	}

	@Override // we can tell type of auth we need. here just basic auth we used
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("john").password(this.passwordEncoder().encode("password123"))
				.roles("NORMAL");
		auth.inMemoryAuthentication().withUser("admin").password(this.passwordEncoder().encode("passwordABC"))
				.roles("ADMIN");

	}

	// ROLE : high level overview eg) NOrmal can only read
	// authority : permissions -> read , write

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		// return NoOpPasswordEncoder.getInstance();// here we are saying that the
		// password we have used above we are not using any facility to ecode it

		return new BCryptPasswordEncoder(10);// a type of password encoding . 10 is the strength of encoding

	}

	/*
	 * 
	 * // ROLE based access ecample
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.authorizeRequests() // we should authorize the request
	 * .antMatchers("/public/**").hasRole("NORMAL") // here we are saying ravi (role
	 * = NORMAL) has access to /public/** after he enters his password. and any
	 * other users dont need to put password .anyRequest() // any request we should
	 * authorized .authenticated() // then authenticate .and() // and then
	 * .httpBasic(); // we use basic authentication method.
	 * 
	 * }
	 */

	/*
	 * FORM based Authentication
	 * 
	 * CLIENT SERVER --- POST username & password -->
	 * 
	 * <------------OK-----------------
	 * 
	 * <----Cookies SESSIONID----------
	 * 
	 * ----any request with SESSIONID-->
	 * 
	 * <----200 ok -------------------
	 */

}
