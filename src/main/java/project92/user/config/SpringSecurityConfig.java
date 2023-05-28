package project92.user.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/build/**", "/images/**", "/css/**", "/js/**", "/vendors/**",
                                "/users/login", "/users/register", "/users/idCheck", "/users/findPassword").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/company/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/warehouse/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/inventory/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/users/login")	// [A] 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/users/login")	// [B] submit 받을 url
                        .usernameParameter("username")	// [C] submit할 아이디 view name=username
                        .passwordParameter("password")	// [D] submit할 비밀번호 view password=password
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                // default -> post (/logout)
                .logout(withDefaults());

        return http.build();
    }
}