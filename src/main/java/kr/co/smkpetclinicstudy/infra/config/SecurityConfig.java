package kr.co.smkpetclinicstudy.infra.config;

import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인가 정책
        http
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/members/signUp", "/api/v1/members/fail").permitAll()
                .requestMatchers("/api/v1/members/admin").hasRole(RoleType.ROLE_ADMIN.getRoleType())
                .requestMatchers("/api/v1/members/user").hasRole(RoleType.ROLE_USER.getRoleType())
                .requestMatchers("/api/v1/members/**").authenticated();

        // 인증 정책
        http
                .formLogin().permitAll()
                .defaultSuccessUrl("/api/v1/members")
                .failureUrl("/api/v1/members/fail");


        // 로그아웃
        http
                .logout()
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("remember-me");

        return http.build();
    }

    private LogoutSuccessHandler logoutSuccessHandler() {

        return (request, response, authentication) -> {
            response.sendRedirect("/login");
        };
    }
}
