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
            "/api/v1/posts",
            "/api/v1/posts/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/admin"
    };

    private static final String[] PERMIT_ADMIN_URL_ARRAY = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/v1/posts",
            "/api/v1/posts/**",
            "/v3/api-docs/**",
            "/api/v1/users",
            "/api/v1/users/**",
            "/admin"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers(PERMIT_ALL_URL_ARRAY).permitAll()
                .antMatchers(PERMIT_USER_URL_ARRAY).hasRole(Role.USER.name())
                .antMatchers(PERMIT_ADMIN_URL_ARRAY).hasRole(Role.ADMIN.name())
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
