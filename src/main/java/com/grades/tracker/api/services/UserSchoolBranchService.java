package com.grades.tracker.api.services;

import com.grades.tracker.api.entities.UserSchoolBranch;
import com.grades.tracker.api.exceptions.ResourceNotFoundException;
import com.grades.tracker.api.repositories.UserSchoolBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSchoolBranchService {
    private UserSchoolBranchRepository userSchoolBranchRepository;

    @Autowired
    public void setUserSchoolBranchRepository(UserSchoolBranchRepository userSchoolBranchRepository) {
        this.userSchoolBranchRepository = userSchoolBranchRepository;
    }

    public UserSchoolBranch getUserSchoolBranchById(int branchId) {
        return userSchoolBranchRepository.findById(branchId).orElseThrow(() -> new ResourceNotFoundException("This branch does not exist"));
    }
}
