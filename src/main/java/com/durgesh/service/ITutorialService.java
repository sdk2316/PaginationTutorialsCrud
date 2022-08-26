package com.durgesh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.durgesh.model.Tutorial;

public interface ITutorialService {
	
	 List<Tutorial> getByPublished(boolean published);
	  List<Tutorial> getByTitleContaining(String title);
	  
	  List<Tutorial> getDataByTitleContaining(String title);
	  
	  
	 List<Tutorial> getAll();
	Tutorial saveTutorial(Tutorial tutorial);
	Optional<Tutorial> getById(long id);
	
	Tutorial updateTutorial( long id,  Tutorial tutorial);
	void deleteById(long id);
	void deleteAllTutorials();
	//.ITutorialServicList<Tutorial> findByPublished(boolean b);
	Page<Tutorial> getAllPage(Pageable paging);
	Page<Tutorial> getByTitleContaining(String title, Pageable paging);
	Page<Tutorial> getByPublished(boolean b, Pageable paging);

}
