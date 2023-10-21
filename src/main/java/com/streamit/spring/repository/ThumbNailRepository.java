package com.streamit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.spring.model.Db.ThumbNail;

public interface ThumbNailRepository  extends JpaRepository<ThumbNail, Integer>{

}
