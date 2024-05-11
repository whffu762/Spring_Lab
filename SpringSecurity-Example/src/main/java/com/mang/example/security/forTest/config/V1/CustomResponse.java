package com.mang.example.security.forTest.config.V1;

import org.apache.catalina.connector.Response;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomResponse extends Response {

    @Override
    public void sendRedirect(String location) throws IOException {
        sendRedirect(location, SC_SEE_OTHER);
    }
}