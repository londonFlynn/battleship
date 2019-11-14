package pro0.battleship.app;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
						return true;
					}
					
					@Override
					public boolean isCredentialsNonExpired() {
						return true;
					}
					
					@Override
					public boolean isAccountNonLocked() {
						return true;
					}
					
					@Override
					public boolean isAccountNonExpired() {
						return true;
					}
					
					@Override
					public String getUsername() {
						String nameInDB = null;
						if(userRepo.findById(username).isPresent()) nameInDB = userRepo.findById(username).get().getUsername();
						
						return nameInDB;
					}
					
					@Override
					public String getPassword() {
						String passInDB = null;
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
									return "User";
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
				.antMatchers("/**").permitAll()
//				.antMatchers(HttpMethod.GET, "/").permitAll()
//				.antMatchers(HttpMethod.GET, "/login").permitAll()
//				.antMatchers(HttpMethod.GET, "/login/*").permitAll()
//				.antMatchers(HttpMethod.POST, "/login").permitAll()
//				.antMatchers(HttpMethod.POST, "/login/*").permitAll()
				
				.anyRequest().authenticated()
				
				.and()
					.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/login/submit")
					.defaultSuccessUrl("/dock", true)
					.failureUrl("/login")
					.usernameParameter("username")
					.passwordParameter("password")
					.permitAll()
					
				.and()
					.logout()
					.logoutUrl("/login/logout")
					.logoutSuccessUrl("/login")
					.clearAuthentication(true)
					
				.and()
					.httpBasic()
					.authenticationEntryPoint(authEntry)
				
				.and()
					.csrf().disable()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		} catch(Exception e) {
			
		}
	}
}
