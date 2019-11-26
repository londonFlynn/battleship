package pro0.battleship.app;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import pro0.battleship.repositories.UserJpaRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserJpaRepository userRepo;
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				return new UserDetails() {
					private static final long serialVersionUID = 1L;
					
					@Override
					public boolean isEnabled() {
						return !getUsername().isEmpty() ? true : false;
					}
					
					@Override
					public boolean isCredentialsNonExpired() {
						return !getUsername().isEmpty() ? true : false;
					}
					
					@Override
					public boolean isAccountNonLocked() {
						return !getUsername().isEmpty() ? true : false;
					}
					
					@Override
					public boolean isAccountNonExpired() {
						return !getUsername().isEmpty() ? true : false;
					}
					
					@Override
					public String getUsername() {
						String nameInDB = "";
						if(userRepo.findById(username).isPresent()) nameInDB = userRepo.findById(username).get().getUsername();
						
						return nameInDB;
					}
					
					@Override
					public String getPassword() {
						String passInDB = "";
						if(userRepo.findById(username).isPresent()) passInDB = "{noop}" + userRepo.findById(username).get().getPassword();
						
						return passInDB;
					}
					
					@Override
					public Collection<? extends GrantedAuthority> getAuthorities() {
						return Arrays.asList(
							new GrantedAuthority() {
								private static final long serialVersionUID = 1L;
		
								@Override
								public String getAuthority() {
									return !getUsername().isEmpty() ? "User" : "";
								}
							}
						);
					}
				};
			}
		};
		
	}

	AuthenticationEntryPoint authEntry = (req, resp, ae) -> {
		resp.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
	};
	
	@Override
	protected void configure(HttpSecurity http) {
		
		try {
			http.authorizeRequests()
//				.antMatchers("/**").permitAll()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/login").permitAll()
				.antMatchers(HttpMethod.GET, "/login/*").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/login/*").permitAll()
				.antMatchers(HttpMethod.GET, "/fromjs/**").permitAll()
				.antMatchers(HttpMethod.POST, "/fromjs/**").permitAll()
				.antMatchers(HttpMethod.GET, "/betweenjs/**").permitAll()
				
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				
				.anyRequest().authenticated()
				
				.and()
					.formLogin()
					
					// Default end point is "/login" but does not use our controller unless specified.
					.loginPage("/login")
					
					.defaultSuccessUrl("/dock", true)
					.permitAll()
					
				.and()
					.logout()
					.clearAuthentication(true)
					
				.and()
					.httpBasic()
					.authenticationEntryPoint(authEntry)
				
				.and()
					.csrf().disable()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		} catch(Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
