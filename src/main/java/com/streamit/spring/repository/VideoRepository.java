package com.streamit.spring.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.spring.model.Db.Video;



public interface VideoRepository extends JpaRepository<Video, Integer> {
	
	public Optional<Video> findByvRef (String ref);
	
	public Page<Video> findByvName(String vName, Pageable pageable);

	public Page<Video> findAll(Pageable pageable);  
	
}
