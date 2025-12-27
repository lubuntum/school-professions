package com.profession.suggest.interceptors.auth;

import com.profession.suggest.services.jwt.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class JWTValidationInterceptor implements HandlerInterceptor {
    private final JWTService jwtService;

    public JWTValidationInterceptor(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false; // Do not proceed with further processing
        }
        try {
            String jwtToken = request.getHeader("Authorization");
            if (jwtToken != null){
                String token = jwtToken.replace("Bearer ", "");
                if (!jwtService.isValid(token)){
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token or credentials");
                    return false;
                }

                request.setAttribute("id", Long.valueOf(jwtService.extractSubject(token)));

                JWTAuth jwtAuth = new JWTAuth(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(jwtAuth);
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token or credentials");
                return false;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return false;
        }
    }
}
