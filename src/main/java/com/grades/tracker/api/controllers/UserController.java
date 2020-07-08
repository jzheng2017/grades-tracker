package com.grades.tracker.api.controllers;

import com.grades.tracker.api.dto.RegistrationDTO;
import com.grades.tracker.api.dto.UserDTO;
import com.grades.tracker.api.entities.User;
import com.grades.tracker.api.services.UserService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users
     *
     * @return ResponseEntity object containing a list of users
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@QuerydslPredicate(root = User.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(predicate, pageable));
    }

    /**
     * Get a single user
     *
     * @param userId the id of a user
     * @return ResponseEntity object containing a user
     */
    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    /**
     * Delete a single user
     *
     * @param userId the id of a user
     * @return a boolean value, true if user has been deleted, otherwise false
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    /**
     * Update a user
     *
     * @param user object containing the new values
     * @return user object containing the updated values
     */
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    /**
     * Add a single user
     *
     * @param user object containing the registration values
     * @return registered user
     */
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody RegistrationDTO user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

}
