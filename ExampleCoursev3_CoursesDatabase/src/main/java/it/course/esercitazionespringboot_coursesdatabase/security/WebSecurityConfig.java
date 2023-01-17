package it.course.esercitazionespringboot_coursesdatabase.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
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
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

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
            .requestMatchers("/apiAuth/signup").access("hasRole('ADMIN')")
            .requestMatchers("/apiAuth/signin").permitAll()
            .requestMatchers("/apiAuth/signout").permitAll()
            .requestMatchers("/apiCourse/insert/**").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiCourse/update/**").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiCourse/delete/**").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiCourse/upload/**").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiCourse/files/**").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiCourse/course/**").permitAll()
            .requestMatchers("/apiUser/user").hasRole("ADMIN")
            .requestMatchers("/apiUser/insert/admin").hasRole("ADMIN")
            .requestMatchers("/apiUser/insert/mod").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiUser/insert/uer").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiUser/delete/{id}").hasRole("ADMIN")
            .requestMatchers("/apiExam/**").access("hasRole('ADMIN') or hasRole('MODERATOR')")
            .requestMatchers("/apiUser/users").permitAll()
            .requestMatchers("/apiUser/**").permitAll()
            .anyRequest().authenticated();

        httpSecurity.authenticationProvider(authenticationProvider());

        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
