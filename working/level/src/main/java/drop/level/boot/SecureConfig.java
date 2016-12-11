package drop.level.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {

	/**
	 * This section defines the user accounts which can be used for
	 * authentication as well as the roles each user has.
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().//
				withUser("tom").password("tom").roles("USER").and().//
				withUser("jack").password("1234").roles("USER", "ADMIN");
	}

	/**
	 * This section defines the security policy for the app.
	 * <p>
	 * <ul>
	 * <li>BASIC authentication is supported (enough for this REST-based
	 * demo).</li>
	 * <li>/employees is secured using URL security shown below.</li>
	 * <li>CSRF headers are disabled since we are only testing the REST
	 * interface, not a web one.</li>
	 * </ul>
	 * NOTE: GET is not shown which defaults to permitted.
	 *
	 * @param http
	 * @throws Exception
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */

	private static String STATIC_SERVER = "http://localhost:8080";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
				.loginPage(STATIC_SERVER + "/login.html").failureUrl(STATIC_SERVER + "/login_failure.html").permitAll()
				.and().logout().permitAll();

		// http.antMatcher("/user/login").csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/test").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/test/**").hasRole("ADMIN").antMatchers(HttpMethod.PATCH, "/test/**")
				.hasRole("ADMIN").and().csrf().disable();
		// http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST,
		// "/test").hasRole("ADMIN")
		// .antMatchers(HttpMethod.PUT,
		// "/test/**").hasRole("ADMIN").antMatchers(HttpMethod.PATCH,
		// "/test/**")
		// .hasRole("ADMIN").and().csrf().disable();

	}
}
