package com.grades.tracker.api.services;

import com.grades.tracker.api.dto.UserSchoolDTO;
import com.grades.tracker.api.entities.QUserSchool;
import com.grades.tracker.api.entities.UserSchool;
import com.grades.tracker.api.exceptions.ResourceNotFoundException;
import com.grades.tracker.api.mappers.UserSchoolMapper;
import com.grades.tracker.api.repositories.UserSchoolRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSchoolService {
    private UserSchoolRepository userSchoolRepository;
    private UserSchoolMapper userSchoolMapper;

    @Autowired
    public void setUserSchoolRepository(UserSchoolRepository userSchoolRepository) {
        this.userSchoolRepository = userSchoolRepository;
    }

    @Autowired
    public void setUserSchoolMapper(UserSchoolMapper userSchoolMapper) {
        this.userSchoolMapper = userSchoolMapper;
    }

    public List<UserSchoolDTO> getAllUserSchools(Predicate predicate, Pageable pageable) {
        predicate = returnPredicateWhenNull(predicate);

        List<UserSchool> listOfUserSchoolEntities = userSchoolRepository.findAll(predicate, pageable).getContent();

        return userSchoolMapper.mapToUserSchoolDTOList(listOfUserSchoolEntities);
    }


    public UserSchoolDTO addUserSchool(UserSchoolDTO userSchoolDTO) {
        return createUpdateUserSchool(userSchoolDTO);
    }


    public UserSchoolDTO updateUserSchool(UserSchoolDTO userSchoolDTO) {
        if (userSchoolRepository.existsById(userSchoolDTO.getId())) {
            return createUpdateUserSchool(userSchoolDTO);
        } else {
            throw new ResourceNotFoundException("User school not found");
        }
    }

    public Boolean deleteUserSchoolById(Integer userSchoolId) {
        if (userSchoolRepository.existsById(userSchoolId)) {
            userSchoolRepository.deleteById(userSchoolId);
            return true;
        } else {
            throw new ResourceNotFoundException(String.format("User school id %d does not exist", userSchoolId));
        }
    }

    private Predicate returnPredicateWhenNull(Predicate predicate) {
        if (predicate == null) {
            return QUserSchool.userSchool.user.id.ne(-1); // bug workaround of QueryDSL Web Support, it returns null when no matching criteria is passed in
        }
        return predicate;
    }

    private UserSchoolDTO createUpdateUserSchool(UserSchoolDTO userSchoolDTO) {
        UserSchool mappedUserSchoolEntity = userSchoolMapper.mapToUserSchool(userSchoolDTO);
        UserSchool savedUserSchoolEntity = userSchoolRepository.save(mappedUserSchoolEntity);

        return userSchoolMapper.mapToUserSchoolDTO(savedUserSchoolEntity);
    }
}
