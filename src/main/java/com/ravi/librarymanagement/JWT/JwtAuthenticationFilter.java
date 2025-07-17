package com.ravi.librarymanagement.JWT;


import com.ravi.librarymanagement.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        //Check if Authorization is present and start with "Bearer "
        if (authHeader == null || !authHeader.startsWith(("Bearer "))){
            filterChain.doFilter(request, response);
            return;
        }

        //Extract JWT Token from header

        jwtToken = authHeader.substring(7);
        username = jwtService.extractUsername(jwtToken);

        //check if we have a username but no authentication exist yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            //Get The Details From database
            var userDetails = userRepository.findByUsername(username)
                    .orElseThrow(()-> new RuntimeException("User not found"));

            //Validate the Token
            if (jwtService.isTokenValid(jwtToken, userDetails)){

                //Create the authentication with user roles
                List<SimpleGrantedAuthority> authorities =
                        userDetails.getRoles()
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());  //Modifiable also you can use just .toList()

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, authorities
                );

                //Set Authentication Details
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Update Security context with authentication
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }

        }

    filterChain.doFilter(request, response);

    }
}
