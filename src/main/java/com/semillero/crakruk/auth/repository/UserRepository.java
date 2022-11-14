package com.semillero.crakruk.auth.repository;


import com.semillero.crakruk.auth.dto.UserBasicDto;
import com.semillero.crakruk.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    UserModel findByEmail(String email);

   /* @Query(value = "SELECT new com.alkemy.ong.dto.UserBasicDto(u.id, u.firstName, u.lastName, u.email) FROM UserModel u")
    List<UserBasicDto> getAllUsers();*/

   /* @Query(value = "SELECT COUNT(id) FROM UserModel")
    int getUsersQuantity();*/
}
