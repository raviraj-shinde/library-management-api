package com.ravi.librarymanagement.JWT;

import com.ravi.librarymanagement.model.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String extractUsername(String jwtToken) {
    }

    public boolean isTokenValid(String jwtToken, User userDetails) {
    }
}
