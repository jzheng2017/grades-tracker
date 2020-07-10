package com.grades.tracker.api.mappers;

import com.grades.tracker.api.dto.UserSchoolDTO;
import com.grades.tracker.api.entities.UserSchool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSchoolMapper extends ClassMapper {

    public UserSchoolDTO mapToUserSchoolDTO(UserSchool user) {
        return modelMapper.map(user, UserSchoolDTO.class);
    }

    public List<UserSchoolDTO> mapToUserSchoolDTOList(List<UserSchool> userList) {
        return userList
                .stream()
                .map(entity -> modelMapper.map(entity, UserSchoolDTO.class))
                .collect(Collectors.toList());
    }

    public UserSchool mapToUserSchool(UserSchoolDTO user) {
        return modelMapper.map(user, UserSchool.class);
    }
}
