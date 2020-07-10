package com.grades.tracker.api.mappers;

import com.grades.tracker.api.dto.UserSchoolBranchDTO;
import com.grades.tracker.api.entities.UserSchoolBranch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserSchoolBranchMapper extends ClassMapper {
    public UserSchoolBranchDTO mapToUserSchoolBranchDTO(UserSchoolBranch user) {
        return modelMapper.map(user, UserSchoolBranchDTO.class);
    }

    public List<UserSchoolBranchDTO> mapToUserSchoolDTOList(List<UserSchoolBranch> userList) {
        return userList
                .stream()
                .map(entity -> modelMapper.map(entity, UserSchoolBranchDTO.class))
                .collect(Collectors.toList());
    }

    public UserSchoolBranch mapToUserSchoolBranch(UserSchoolBranchDTO user) {
        return modelMapper.map(user, UserSchoolBranch.class);
    }
}
