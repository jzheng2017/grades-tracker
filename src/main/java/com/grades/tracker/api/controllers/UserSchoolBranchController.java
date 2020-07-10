package com.grades.tracker.api.controllers;

import com.grades.tracker.api.dto.UserSchoolBranchDTO;
import com.grades.tracker.api.entities.UserSchoolBranch;
import com.grades.tracker.api.services.UserSchoolBranchService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-school-branch")
public class UserSchoolBranchController {
    private UserSchoolBranchService userSchoolBranchService;

    @Autowired
    public void setUserSchoolBranchService(UserSchoolBranchService userSchoolBranchService) {
        this.userSchoolBranchService = userSchoolBranchService;
    }

    @GetMapping
    public ResponseEntity<List<UserSchoolBranchDTO>> getUserSchoolBranch(@QuerydslPredicate(root = UserSchoolBranch.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(userSchoolBranchService.getUserSchoolBranch(predicate, pageable));
    }

    @PostMapping
    public ResponseEntity<UserSchoolBranchDTO> createUserSchoolBranch(@Valid @RequestBody UserSchoolBranchDTO userSchoolBranchDTO) {
        return ResponseEntity.ok(userSchoolBranchService.createUserSchoolBranch(userSchoolBranchDTO));
    }

    @PutMapping
    public ResponseEntity<UserSchoolBranchDTO> updateUserSchoolBranch(@Valid @RequestBody UserSchoolBranchDTO userSchoolBranchDTO) {
        return ResponseEntity.ok(userSchoolBranchService.updateUserSchoolBranch(userSchoolBranchDTO));
    }
}
