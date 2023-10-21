package com.streamit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.spring.model.Db.Login;


public interface LoginRepository extends JpaRepository<Login, Integer>{
	
	
	boolean existsByUsernameAndPassword (String username, String password);

}
