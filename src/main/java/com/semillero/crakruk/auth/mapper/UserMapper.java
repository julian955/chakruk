package com.semillero.crakruk.auth.mapper;


import com.semillero.crakruk.auth.dto.UserBasicDto;
import com.semillero.crakruk.auth.dto.UserDto;
import com.semillero.crakruk.auth.dto.UserPatchDto;
import com.semillero.crakruk.auth.dto.UserProfileDto;
import com.semillero.crakruk.auth.model.UserModel;
import com.semillero.crakruk.model.Role;
import com.semillero.crakruk.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    RoleRepository roleRepository;

    public UserModel userDTO2Entity(UserDto dto){

        UserModel entity = new UserModel();
        entity.setUser(dto.getUser());
        entity.setId(dto.getId());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setPhoto(dto.getPhoto());
        entity.setCreated(dto.getCreated());
        entity.setUpdated(dto.getUpdated());
        entity.setDeleted(false);
        entity.setRole(dto.getRole());

        return entity;
    }

    public UserDto userEntity2DTO(UserModel entity){
        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setUser(entity.getUser());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setPhoto(entity.getPhoto());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        dto.setRole(entity.getRole());

        return dto;
    }

    public List<UserDto> listEntity2DTO(List<UserModel> entityList){

        List<UserDto> dtoList = new ArrayList<>();

        entityList.forEach(entity ->
                dtoList.add(userEntity2DTO(entity))
        );



        return dtoList;
    }

    public UserBasicDto userEntity2UserBasicDto(UserModel entity) {
        UserBasicDto userBasicDto = new UserBasicDto();
        userBasicDto.setEmail(entity.getEmail());
        userBasicDto.setUser(entity.getUser());
        userBasicDto.setId(entity.getId());
        userBasicDto.setPhoto(entity.getPhoto());

        return userBasicDto;

    }

    public UserProfileDto userModel2UserProfileDto(UserModel userModel) {
        UserProfileDto userProfileDto = new UserProfileDto();

        userProfileDto.setEmail(userModel.getEmail());
        userProfileDto.setUser(userModel.getUser());
        userProfileDto.setPhoto(userModel.getPhoto());
        return userProfileDto;

    }

    public UserPatchDto userModel2UserPatchDto(UserModel userModel){
        return new UserPatchDto(userModel.getUser(), userModel.getPhoto());
    }

}
