package com.grades.tracker.api.controllers;

import com.grades.tracker.api.dto.UserSchoolDTO;
import com.grades.tracker.api.entities.UserSchool;
import com.grades.tracker.api.services.UserSchoolService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-school")
public class UserSchoolController {
    private UserSchoolService userSchoolService;

    @Autowired
    public void setUserSchoolService(UserSchoolService userSchoolService) {
        this.userSchoolService = userSchoolService;
    }

    @GetMapping
    public ResponseEntity<List<UserSchoolDTO>> getAllUserSchools(@QuerydslPredicate(root = UserSchool.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(userSchoolService.getAllUserSchools(predicate, pageable));
    }

    @PostMapping
    public ResponseEntity<UserSchoolDTO> addUpdateUserSchool(@RequestBody UserSchoolDTO userSchoolDTO) {
        return ResponseEntity.ok(userSchoolService.addUserSchool(userSchoolDTO));
    }


    @PutMapping
    public ResponseEntity<UserSchoolDTO> updateUserSchool(@RequestBody UserSchoolDTO userSchoolDTO) {
        return ResponseEntity.ok(userSchoolService.updateUserSchool(userSchoolDTO));
    }

    @DeleteMapping("{userSchoolId}")
    public ResponseEntity<Boolean> deleteUserSchool(@PathVariable("userSchoolId") Integer userSchoolId) {
        return ResponseEntity.ok(userSchoolService.deleteUserSchoolById(userSchoolId));
    }
}
