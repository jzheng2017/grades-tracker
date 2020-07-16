package com.grades.tracker.api.services;

import com.grades.tracker.api.entities.User;
import com.grades.tracker.api.exceptions.UnauthorizedActionException;
import com.grades.tracker.api.repositories.UserRepository;
import com.grades.tracker.api.util.JwtUtil;
import com.grades.tracker.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public boolean authorizeByUsernameWithJwtToken(String authorizationHeader, int userId) {
        String username = jwtUtil
                .getBodyFromToken(Util.getTokenFromHeaderString(authorizationHeader))
                .getSubject();

        User authorizedUser = userRepository.findByUsername(username).orElseThrow(() -> new UnauthorizedActionException("No user found"));

        return authorizedUser.getId().equals(userId);
    }
}
