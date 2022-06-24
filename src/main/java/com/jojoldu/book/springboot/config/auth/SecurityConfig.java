package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    private static final String[] PERMIT_ALL_URL_ARRAY = {
            "/",
            "/css/**",
            "/images/**",
            "/js/**",
            "/h2-console/**",
            "/profile"
    };

    private static final String[] PERMIT_USER_URL_ARRAY = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/user/**",
            "/v3/api-docs/**",
            "/users/**"
    };

    private static final String[] PERMIT_ADMIN_URL_ARRAY = {
            "/api/admin/**",
            "/admin/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers(PERMIT_ADMIN_URL_ARRAY).hasRole(Role.ADMIN.name())
                .antMatchers(PERMIT_USER_URL_ARRAY).hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(PERMIT_ALL_URL_ARRAY).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
