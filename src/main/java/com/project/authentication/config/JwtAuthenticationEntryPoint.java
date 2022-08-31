package com.project.authentication.config;
;
import com.alibaba.fastjson.JSON;
import com.project.authentication.dto.ResponseApplication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseApplication<Object>
                errors = ResponseApplication.builder().localDateTime(LocalDateTime.now())
                .result("UNAUTORIZED").build();
        String result = JSON.toJSONString(errors, true);
        out.print(result);
        out.flush();
    }
}
