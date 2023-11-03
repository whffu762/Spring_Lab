package com.mang.example.security.forTest.config.V3;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, CustomLoginSuccessHandler customLoginSuccessHandler){
        this.authenticationManager = authenticationManager;
        this.customLoginSuccessHandler = customLoginSuccessHandler;
    }

    public CustomAuthenticationFilter(){
        super.setAuthenticationManager(authenticationManager);
        super.setFilterProcessesUrl("/user/login");
        super.setAuthenticationSuccessHandler(customLoginSuccessHandler);
        super.afterPropertiesSet();
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("V3 Filter 호출");
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(request.getParameter("userEmail"), request.getParameter("userPw"));
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
