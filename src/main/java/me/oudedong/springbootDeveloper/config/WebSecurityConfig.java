//package me.oudedong.springbootDeveloper.config;
//
//import lombok.RequiredArgsConstructor;
//import me.oudedong.springbootDeveloper.service.UserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig { //스프링보안체인필터에서 참조할 스프링 빈들!
//
//    private final UserDetailService userService;
//
//    @Bean
//    public WebSecurityCustomizer configure(){
//        return (web -> web.ignoring()
//                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
//                .requestMatchers(new AntPathRequestMatcher("/static/**"))
//        );
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http.authorizeHttpRequests(
//                auth->auth.requestMatchers(
//                        new AntPathRequestMatcher("/login"),
//                        new AntPathRequestMatcher("/signup"),
//                        new AntPathRequestMatcher("/user"),
//                        new AntPathRequestMatcher("/guest")
//                ).permitAll().anyRequest().authenticated()
//        ).formLogin(
//                formLogin->formLogin
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/articles")
//        ).logout(
//                logout->logout
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true)
//        ).csrf(AbstractHttpConfigurer::disable).build();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailService userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception{
//
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return new ProviderManager(authProvider);
//    }
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
