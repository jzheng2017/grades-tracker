package com.grades.tracker.api.services;

import com.grades.tracker.api.dto.RegistrationDTO;
import com.grades.tracker.api.dto.UserDTO;
import com.grades.tracker.api.entities.QUser;
import com.grades.tracker.api.entities.User;
import com.grades.tracker.api.exceptions.BadParameterException;
import com.grades.tracker.api.exceptions.DuplicateEntryException;
import com.grades.tracker.api.exceptions.ResourceNotFoundException;
import com.grades.tracker.api.exceptions.UnauthorizedActionException;
import com.grades.tracker.api.mappers.UserMapper;
import com.grades.tracker.api.repositories.UserRepository;
import com.grades.tracker.api.services.interfaces.HashService;
import com.grades.tracker.api.util.Util;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private HashService hashService;
    private UserMapper userMapper;
    private AuthorizationService authorizationService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setHashService(HashService hashService) {
        this.hashService = hashService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    public List<UserDTO> getAllUsers(Predicate predicate, Pageable pageable) {
        predicate = returnPredicateWhenNull(predicate);

        List<User> userList = userRepository.findAll(predicate, pageable).toList();

        return userMapper.mapToUserDTOList(userList);
    }

    public UserDTO getUser(int userId) {
        return userMapper
                .mapToUserDTO(
                        userRepository
                                .findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("No user found")));
    }

    public boolean deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            throw new ResourceNotFoundException("User can not be deleted. The given user does not exist.");
        }
    }

    public UserDTO updateUser(String authorizationHeader, UserDTO user) {
        if (user == null) {
            throw new BadParameterException("User is null");
        }

        boolean isAuthorized = authorizationService.authorizeByUsernameWithJwtToken(authorizationHeader, user.getId());

        if (!isAuthorized) {
            throw new UnauthorizedActionException("You are not authorized");
        }

        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("No user found"));

        Util.copyNonNullProperties(user, existingUser);

        return userMapper.mapToUserDTO(userRepository.save(existingUser));
    }

    public UserDTO addUser(RegistrationDTO user) {
        if (user == null) {
            throw new BadParameterException("The registration is null");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateEntryException("Username already exists");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateEntryException("Email already exists");
        }

        String encodedPassword = hashService.encode(user.getPassword());

        user.setPassword(encodedPassword);

        User mappedUser = userMapper.mapToUser(user);
        User savedUser = userRepository.save(mappedUser);

        return userMapper.mapToUserDTO(savedUser);
    }

    private Predicate returnPredicateWhenNull(Predicate predicate) {
        if (predicate == null) {
            return QUser.user.id.ne(-1); // bug workaround of QueryDSL Web Support, it returns null when no matching criteria is passed in
        }
        return predicate;
    }
}
