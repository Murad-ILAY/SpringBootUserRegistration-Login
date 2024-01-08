package Demo.user.registration.and.login;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Configure the SecurityFilterChain bean
   
    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/list_users").authenticated()
                .anyRequest().permitAll()
            .and()
            .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/list_users")
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/")
                .permitAll();

        // Apply the authenticationProvider to the HttpSecurity instance
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {

//	http.authorizeRequests().antMatcher("/list_users").authenticated().anyRequest().permitAll().and().formLogin().usernameParameter("email").defaultSuccessUrl("/list_uses")
//	.and().logout().logoutSuccessUrl("/").permitAll();
//	
//		http.authorizeHttpRequests(authorizeRequests ->authorizeRequests.antMatchers("/list_users").permitAll().anyRequest().authenticated()).formLogin(formLogin ->formLogin.loginPage("/login".permitAll())
//        .rememberMe(withDefaults());
//	}
	
	

