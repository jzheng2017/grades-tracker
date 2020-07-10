package com.grades.tracker.api.services;

import com.grades.tracker.api.dto.UserSchoolBranchDTO;
import com.grades.tracker.api.entities.QUserSchoolBranch;
import com.grades.tracker.api.entities.UserSchoolBranch;
import com.grades.tracker.api.exceptions.ResourceNotFoundException;
import com.grades.tracker.api.mappers.UserSchoolBranchMapper;
import com.grades.tracker.api.repositories.UserSchoolBranchRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSchoolBranchService {
    private UserSchoolBranchRepository userSchoolBranchRepository;
    private UserSchoolBranchMapper userSchoolBranchMapper;

    @Autowired
    public void setUserSchoolBranchRepository(UserSchoolBranchRepository userSchoolBranchRepository) {
        this.userSchoolBranchRepository = userSchoolBranchRepository;
    }

    @Autowired
    public void setUserSchoolBranchMapper(UserSchoolBranchMapper userSchoolBranchMapper) {
        this.userSchoolBranchMapper = userSchoolBranchMapper;
    }

    public List<UserSchoolBranchDTO> getUserSchoolBranch(Predicate predicate, Pageable pageable) {
        predicate = returnPredicateWhenNull(predicate);

        List<UserSchoolBranch> listOfUserSchoolBranchEntities = userSchoolBranchRepository.findAll(predicate, pageable).getContent();

        return userSchoolBranchMapper.mapToUserSchoolDTOList(listOfUserSchoolBranchEntities);
    }

    public UserSchoolBranchDTO createUserSchoolBranch(UserSchoolBranchDTO userSchoolBranchDTO) {
        return createUpdateUserSchoolBranch(userSchoolBranchDTO);
    }

    public UserSchoolBranchDTO updateUserSchoolBranch(UserSchoolBranchDTO userSchoolBranchDTO) {
        if (userSchoolBranchRepository.existsById(userSchoolBranchDTO.getId())) {
            return createUpdateUserSchoolBranch(userSchoolBranchDTO);
        } else {
            throw new ResourceNotFoundException(String.format("User school branch with the id %d does not exist.", userSchoolBranchDTO.getId()));
        }
    }

    private UserSchoolBranchDTO createUpdateUserSchoolBranch(UserSchoolBranchDTO userSchoolBranchDTO) {
        UserSchoolBranch mappedUserSchoolBranchEntity = userSchoolBranchMapper.mapToUserSchoolBranch(userSchoolBranchDTO);
        UserSchoolBranch savedUserSchoolBranchEntity = userSchoolBranchRepository.save(mappedUserSchoolBranchEntity);

        return userSchoolBranchMapper.mapToUserSchoolBranchDTO(savedUserSchoolBranchEntity);
    }

    private Predicate returnPredicateWhenNull(Predicate predicate) {
        if (predicate == null) {
            return QUserSchoolBranch.userSchoolBranch.userSchool.id.ne(-1); // bug workaround of QueryDSL Web Support, it returns null when no matching criteria is passed in
        }
        return predicate;
    }
}
