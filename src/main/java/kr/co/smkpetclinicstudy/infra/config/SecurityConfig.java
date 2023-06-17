package kr.co.smkpetclinicstudy.infra.config;

import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {   // Spring Security 5.7.0 부터는 WebSecurityConfigureAdapter 를 더이상 지원하지 않는다

    private final CustomUserDetailsService customUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(customUserDetailsService);

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인가 정책
        http
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/members/user/**").authenticated()
                .requestMatchers("/api/v1/members/admin").hasRole(RoleType.ADMIN_ROLE.getRoleType())
                .anyRequest().permitAll();

        // 인증 정책
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/api/v1/members")
                .failureUrl("/api/v1/members/fail");


        // 로그아웃
        http
                .logout()
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("remember-me");

        http
                .csrf().disable();

        return http.build();
    }

    private LogoutSuccessHandler logoutSuccessHandler() {

        return (request, response, authentication) -> {
            response.sendRedirect("/login");
        };
    }
}
