package kr.co.smkpetclinicstudy.infra.config;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {       // Spring Security 5.7 이상 버전부터 WebSecurityConfigureAdapter Deprecated

    private final UserDetailsService userDetailsService;


    // Bean : UserDetailsService
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean   // Bean : BCryptPasswordEncoder
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    // Configure HTTP security : SecurityFilterChain 반환하여 Bean 등록함으로써 Component 기반의 보안 설정이 가능하다
    // FilterChain : 모든 Request 가 Servlet 에 도달하기 전에 거쳐야한다
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인가 정책 : 요청에 대한 접근 권한을 결정하기 위함
        http
                .authorizeHttpRequests()            // 요청에 의한 보안검사 시작
                .requestMatchers(request -> request.getRequestURI().equals("/main_page")).permitAll()
                .requestMatchers(request -> request.getRequestURI().equals("/admin_page")).hasRole("ROLE_ADMIN")
                .requestMatchers(request -> request.getRequestURI().equals("/user_page")).authenticated()
                .anyRequest().authenticated();      // 어떠한 URI 접근 시 인증이 필요하도록 설정

        // 인증 정책 :
        http
                .formLogin()            // Login Form 방식으로 인증
                .loginPage("/login")    // 보여줄 Login URL 설정
                .usernameParameter("username")      // Login 페이지의 username ID 값을 설정
                .passwordParameter("password")      // Login 페이지의 password ID 값을 설정
                .failureUrl("/login?error=true")    // Login 실패 후 URL 설정
                .successHandler(authenticationSuccessHandler())         // 인증 성공한 다음 해야할 일을 설정
                .failureHandler(authenticationFailureHandler())         // 인증 실패한 다음 해야할 일을 설정
                .defaultSuccessUrl("/user_page");                       // 인증 성공할 경우, 자동으로 이동하는 URL 설정

        // 로그아웃
        http
                .logout()                       // Logout 처리
                .logoutUrl("/logout")           // Logout 처리 URL
                .logoutSuccessUrl("/login")     // Logout 성공 URL
                .deleteCookies("JSESSIONID", "Remember-me")
                .logoutSuccessHandler(logoutSuccessHandler())   // Logout 성공한 다음 해야할 일을 설정
                .addLogoutHandler(addLogoutHandler());          // Logout 할 때, 사용자가 지정할 행동을 설정

        return http.build();
    }

    @Bean   // Configure Web security : Spring Security Rule; Ignore URL
    public WebSecurityCustomizer securityCustomizer() {

        return (web) -> web
                .ignoring()     // ignoring() 메서드 다음에는 antMatchers() 메서드를 사용할 수 없음
                .requestMatchers(
                        new AntPathRequestMatcher("/images/**", null),
                        new AntPathRequestMatcher("/js/**", null),
                        new AntPathRequestMatcher("/webjars/**", null)
                );
    }



    // 인증이 성공했을 경우, 해야할 일을 설정
    private AuthenticationSuccessHandler authenticationSuccessHandler() {

        return (request, response, authentication) -> {
            System.out.println("Authentication Success" + authentication.getName());
            response.sendRedirect("/home");
        };
    }

    // 인증이 실패했을 경우, 해야할 일을 설정
    private AuthenticationFailureHandler authenticationFailureHandler() {

        return (request, response, exception) -> {
            System.out.println("Authentication Failure" + exception.getMessage());
            response.sendRedirect("/login?error");
        };
    }

    // 로그아웃에 성공했을 경우, 해야할 일을 설정
    private LogoutSuccessHandler logoutSuccessHandler() {

        return (request, response, authentication) -> {
            response.sendRedirect("/login");
        };
    }

    private LogoutHandler addLogoutHandler() {

        return (request, response, authentication) -> {
            System.out.println("Logout Success!");
            HttpSession session = request.getSession();
            session.invalidate();
        };
    }
}
