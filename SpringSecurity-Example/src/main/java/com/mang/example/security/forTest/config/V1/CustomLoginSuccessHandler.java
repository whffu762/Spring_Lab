package com.mang.example.security.forTest.config.V1;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

    RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.sendRedirect(savedRequest.getRedirectUrl());
    }
}

//public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    RequestCache requestCache = new HttpSessionRequestCache();
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//
//        log.info("V1 SuccessHandler 호출");
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//
//        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
//        response.sendRedirect(savedRequest.getRedirectUrl());
//    }
//}