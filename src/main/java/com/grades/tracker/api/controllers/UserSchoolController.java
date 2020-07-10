package com.grades.tracker.api.controllers;

import com.grades.tracker.api.dto.UserSchoolDTO;
import com.grades.tracker.api.entities.UserSchool;
import com.grades.tracker.api.services.UserSchoolService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-school")
public class UserSchoolController {
    private UserSchoolService userSchoolService;

    @Autowired
    public void setUserSchoolService(UserSchoolService userSchoolService) {
        this.userSchoolService = userSchoolService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<UserSchoolDTO>> getAllSchoolsOfUser(@QuerydslPredicate(root = UserSchool.class) Predicate predicate, @PathVariable(name = "userId") int userId, Pageable pageable) {
        return ResponseEntity.ok(userSchoolService.getAllSchoolsOfUser(predicate, pageable, userId));
    }

}
