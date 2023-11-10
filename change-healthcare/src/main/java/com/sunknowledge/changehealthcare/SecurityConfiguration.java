package com.sunknowledge.changehealthcare;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			 .antMatchers(HttpMethod.TRACE, "/**").denyAll()
	         .antMatchers(HttpMethod.PATCH, "/**").denyAll()
	         .antMatchers(HttpMethod.PUT, "/**").denyAll()
	         .antMatchers(HttpMethod.DELETE, "/**").denyAll()
	         .antMatchers(HttpMethod.HEAD, "/**").denyAll()
			 .antMatchers("/").permitAll()
//			 .antMatchers("/v2/api-docs").permitAll()
//			 .antMatchers("/configuration/ui").permitAll()
//			 .antMatchers("/swagger-resources/**").permitAll()
//			 .antMatchers("/configuration/**").permitAll()
//			 .antMatchers("/swagger-ui.html").permitAll()
//			 .antMatchers("/webjars/**").permitAll()
			 .and().logout()
			 .logoutUrl("/logout")
			 .logoutSuccessUrl("/login?logout")
			 .deleteCookies("CHANGEHEALTHCARE_SESSION_ID", "SESSION")
			 .invalidateHttpSession(true).deleteCookies("CHANGEHEALTHCARE_SESSION_ID", "JSESSIONID").permitAll();
		http.sessionManagement().sessionFixation().migrateSession()
			.maximumSessions(1)
			.expiredUrl("/"); 
		http.exceptionHandling().accessDeniedPage("/error");
		http.headers().disable();
		http.csrf().disable();
	}
	
	@Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Bean
    public HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");

    }
}
	