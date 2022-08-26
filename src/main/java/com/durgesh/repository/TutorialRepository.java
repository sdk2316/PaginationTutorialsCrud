package com.durgesh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	  List<Tutorial> findByPublished(boolean published);
	  List<Tutorial> findByTitleContaining(String title);
	  
	  // pagination 
	  Page<Tutorial> findByPublished(boolean published, Pageable pageable);
	  Page<Tutorial> findByTitleContaining(String title, Pageable pageable);
	}
