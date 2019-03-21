package org.sid.sec;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Bilel on 21/03/2019.
 * Ce filtre il intervient pour chaque requete.
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Vérifie s'il contient JWT.
        // Appelle une librairie pour signer
        // Si tout va bien Tu peux passer
        String jwt = httpServletRequest.getHeader("authorization");
        if(jwt == null) throw new RuntimeException("Not authorized");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
