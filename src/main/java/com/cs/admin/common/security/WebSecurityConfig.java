package com.cs.admin.common.security;

import com.cs.admin.system.auth.service.OnlineService;
import com.cs.admin.common.filter.SecurityAuthenticationFilter;
import com.cs.admin.common.jwt.JwtTokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author xiao kun
 * @version 1.0
 * @since 2020/3/11 15:52
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenGenerator jwtTokenGenerator;

    private final OnlineService onlineService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/auth/captcha",
                        "/auth/login",
                        "/v3/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/webjars/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(new TokenAuthenticationEntryPoint())
                .accessDeniedHandler(new SecurityAccessDeniedHandler())
                .and().addFilterBefore(new SecurityAuthenticationFilter(jwtTokenGenerator, onlineService), UsernamePasswordAuthenticationFilter.class);
    }
}
