package com.mang.example.security.forTest.config.V4;

import com.mang.example.security.app.user.service.UserDetailsServiceImpl;
import com.mang.example.security.forTest.config.V1.CustomLoginSuccessHandler;
import com.mang.example.security.forTest.testClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@Component
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider(){
        return new CustomAuthenticationProvider(userDetailsService, bCryptPasswordEncoder(), testClass());
    }
    @Bean
    testClass testClass(){
        return new testClass(bCryptPasswordEncoder());
    }
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception{
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
        customAuthenticationFilter.afterPropertiesSet();

        return customAuthenticationFilter;
    }


    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // /about 요청에 대해서는 로그인을 요구함
                .antMatchers("/about").authenticated()
                // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/spring-security-redirect/postRequest").authenticated()
                // 나머지 요청에 대해서는 로그인을 요구하지 않음
                .anyRequest().permitAll()
                .and()
                // 로그인하는 경우에 대해 설정함
            .formLogin()
                // 로그인 페이지를 제공하는 URL 을 설정함
                .loginPage("/user/loginView")
                // 로그인 성공 URL 을 설정함
                .successForwardUrl("/index")
                // 로그인 실패 URL 을 설정함
                .failureForwardUrl("/index")
                .permitAll()
                .and()
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }
}
