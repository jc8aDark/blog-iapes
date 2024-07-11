package com.iapes.iapes.config.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import com.iapes.iapes.config.security.filter.JwtTokenValidator;
import com.iapes.iapes.service.UserDetailServiceImpl;
import com.iapes.iapes.utils.JwtUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtUtils jwtUtils;
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider authenticationProvider) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // EndPoints_publicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    

                    // EndPoints_Privados_Usuarios
                    http.requestMatchers(HttpMethod.GET, "/api/categories").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/api/categories").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.PUT, "/api/categories/{id}").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/categories/{id}").hasAuthority("DELETE");
                    
                    
                    
                    http.requestMatchers(HttpMethod.GET, "/method/get").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/method/post").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.DELETE, "/method/delete").hasAuthority("DELETE");
                    http.requestMatchers(HttpMethod.PUT, "/method/put").hasAuthority("UPDATE");
                    
                    
                    http.requestMatchers(HttpMethod.GET, "/api/users").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/api/users").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAuthority("READ");
                    
                    //Enpoins_Privados_Privilegios
                  //  http.requestMatchers(HttpMethod.GET, "/api/get/{id}").hasAuthority("READ");
                   // http.requestMatchers(HttpMethod.GET, "/api/geting").hasAuthority("READ");
                   // http.requestMatchers(HttpMethod.POST, "/api/post").hasAuthority("CREATE");
                    
                  //Enpoins_Privados_Categories
                   
                 
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }	

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
