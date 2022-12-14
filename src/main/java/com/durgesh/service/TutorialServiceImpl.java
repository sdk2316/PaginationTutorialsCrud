package com.durgesh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.durgesh.exception.ResourceNotFoundException;
import com.durgesh.model.Tutorial;
import com.durgesh.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements ITutorialService {

	@Autowired
	private TutorialRepository tutorialRepository;

	

	@Override
	public List<Tutorial> getByTitleContaining(String title) {
		
		return tutorialRepository.findAll();
	}

	@Override
	public List<Tutorial> getByPublished(boolean published) {
		// TODO Auto-generated method stub
		return tutorialRepository.findByPublished(published);
	}
	
	@Override
	public List<Tutorial> getAll() {
		// TODO Auto-generated method stub
		return tutorialRepository.findAll();
	}

	@Override
	public Tutorial saveTutorial(Tutorial tutorial) {
		// TODO Auto-generated method stub
		return tutorialRepository.save(tutorial);
	}

	@Override
	public Optional<Tutorial> getById(long id) {
		// TODO Auto-generated method stub
		return tutorialRepository.findById(id);
	}

	@Override
	public Tutorial updateTutorial(long id, Tutorial tutorial) {
		// TODO Auto-generated method stub
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			tutorialRepository.save(_tutorial);
			return _tutorial;
		} else
			throw new ResourceNotFoundException("Not Found");

	}

	@Override
	public void deleteById(long id) {
		tutorialRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllTutorials() {
		tutorialRepository.deleteAll();
		
	}

	@Override
	public List<Tutorial> getDataByTitleContaining(String title) {
		// TODO Auto-generated method stub
		return tutorialRepository.findByTitleContaining(title);
	}

	@Override
	public Page<Tutorial> getAllPage(Pageable paging) {
		// TODO Auto-generated method stub
		return tutorialRepository.findAll(paging);
	}

	@Override
	public Page<Tutorial> getByTitleContaining(String title, Pageable paging) {
		// TODO Auto-generated method stub
		return tutorialRepository.findByTitleContaining(title, paging);
	}

	@Override
	public Page<Tutorial> getByPublished(boolean b, Pageable paging) {
		// TODO Auto-generated method stub
		return tutorialRepository.findByPublished(b,paging);
	}
	
	
}
