package com.bombayShaving.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.bombayShaving.entites.Login;


public interface LoginRepo extends JpaRepository<Login,Integer> {
   public Login findByUserName(String userName);
//   boolean existsUserName(String userName);
   
}

