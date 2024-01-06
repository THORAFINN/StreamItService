package com.streamit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.spring.model.Db.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	/**
	 * 
	 * <p>
	 * @param loginId
	 * @param roleId
	 * @return
	 */
	public Optional<User> findByLogin_idAndRid (int loginId, int roleId);
	
	public Optional<User> findByLogin_id (int loginId);
}
