package it.course.esercitazionespringboot_coursesdatabase.security;

import it.course.esercitazionespringboot_coursesdatabase.security.jwt.AuthEntryPointJwt;
import it.course.esercitazionespringboot_coursesdatabase.security.jwt.AuthTokenFilter;
import it.course.esercitazionespringboot_coursesdatabase.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }



  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .requestMatchers("/apiAuth/signup").hasRole("ADMIN")
        .requestMatchers("/apiAuth/signin").permitAll()
        .requestMatchers("/apiAuth/signout").permitAll()
        .requestMatchers("/apiCourse/").permitAll()
        .requestMatchers("/apiCourse/**").hasRole("USER")
        .requestMatchers("/apiUser/user").hasRole("ADMIN")
        .requestMatchers("/apiUser/insert/admin").hasRole("ADMIN")
        .requestMatchers("/apiUser/insert/mod").hasRole("ADMIN")
        .requestMatchers("/apiUser/insert/user").hasRole("ADMIN")
        .requestMatchers("/apiUser/delete/{id}").hasRole("ADMIN")
        .requestMatchers("/apiExam/**").hasRole("ADMIN")
        .requestMatchers("/apiUser/users").permitAll()
        .anyRequest().authenticated();
    
    httpSecurity.authenticationProvider(authenticationProvider());

    httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
    return httpSecurity.build();
  }
}
