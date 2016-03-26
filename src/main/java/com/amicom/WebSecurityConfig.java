package com.amicom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amicom.service.security.LoginUserDetailsService;


@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	LoginUserDetailsService loginUserDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 1) 특정 요청에 대해서는 시큐리티 설정을 무시하도록 하는 등 전체에 관한 설정을 함
		web.ignoring().antMatchers("/webjars/**", "/css/**", "/img/**", "/js/**", "/less/**", "/dist/**",
				"/bower_components/**", "/NormalFile/**");
	}
	
	@Override
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected void configure(HttpSecurity http) throws Exception {
    	// 2) 특정 요청에 대해서는 시큐리티 설정을 무시하도록 하는 등 전체에 관한 설정을 함
    	http
    		.authorizeRequests()
    			.antMatchers("/test/*").permitAll()
    			.antMatchers("/amicommember/insert").permitAll()
    			.antMatchers("/loginForm").permitAll()
    			.antMatchers("/amicommember/isDuplicate").permitAll()
                .antMatchers("/amicommember/loginForm").permitAll()
                .antMatchers("/amicommember/process").permitAll()
                .antMatchers("/login/process").permitAll()
                .anyRequest().authenticated()
                .and()
           .formLogin()
           		.loginProcessingUrl("/amicommember/process")
                .loginPage("/amicommember/loginForm")
                .failureUrl("/amicommember/loginForm")
                .defaultSuccessUrl("/index", true)
                .usernameParameter("username").passwordParameter("password")
                .and()
            .logout()
            	.logoutUrl("/amicommember/logout")
        		.permitAll()
                .logoutSuccessUrl("/amicommember/loginForm")
                .and()
           .csrf().disable();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("username").password("password").roles("USER");
	}
	
    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        LoginUserDetailsService loginUserDetailsService;

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(loginUserDetailsService)
//            .passwordEncoder(passwordEncoder())
            ;
        }
    }

}