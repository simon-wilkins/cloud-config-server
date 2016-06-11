package a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ADMIN", "USER")
			.and()
			.withUser("user").password("user").roles("USER");
    }

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http
//	        .authorizeRequests()
//	        	.antMatchers("/admin/**").hasRole("ADMIN")
//	        	.antMatchers("/").permitAll()
//	        	//.antMatchers("/**").hasRole(Role.USER.toString())
//	        .anyRequest().authenticated()
//	        .and().formLogin()
//	        .and().httpBasic()
//	        .and().csrf().disable();
//	}
	
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//            .withUser("user")
//            .password("user")
//            .roles("USER")
//        .and()
//            .withUser("admin")
//            .password("admin")
//            .roles("ADMIN","USER");
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests().antMatchers("/login").permitAll()
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
				.exceptionHandling().accessDeniedPage("/access?error");
	}
	
//	@Configuration
//	public static class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
////	  @Autowired
////	  AccountRepository accountRepository;
//
//	  @Override
//	  public void init(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.userDetailsService(userDetailsService());
//	  }
//
//	  @Bean
//	  UserDetailsService userDetailsService() {
//	    return new UserDetailsService() {
//
//	      @Override
//	      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	        return new User(username, "bob", true, true, true, true,
//	                AuthorityUtils.createAuthorityList(Role.USER.toString()));
//	        };
//	        
//	    };
//	  }
//	}

}
