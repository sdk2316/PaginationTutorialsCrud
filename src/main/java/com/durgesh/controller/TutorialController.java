package com.durgesh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.model.Tutorial;
import com.durgesh.service.ITutorialService;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api/tutorials")

public class TutorialController {

	@Autowired
	ITutorialService tutorialService;

	@GetMapping("/getTutorialsWithTilte")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			if (title == null)
				// tutorialRepository.findAll().forEach(tutorials::add);
				tutorialService.getAll().forEach(tutorials::add);
			else
				tutorialService.getByTitleContaining(title).forEach(tutorials::add);
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/findTutorialsByPublished")
	  public ResponseEntity<List<Tutorial>> findTutorialsByPublished() {
	    try {
	      List<Tutorial> tutorials = tutorialService.getByPublished(true);
	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/getTutorialById/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialService.getById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/createTutorial")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialService.saveTutorial(tutorial);
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateTutorialById/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		try {
			return new ResponseEntity<>(tutorialService.updateTutorial(id, tutorial), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/deleteTutorialById/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	    	tutorialService.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	
	@DeleteMapping("/deleteAllTutorials")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    try {
	    	tutorialService.deleteAllTutorials();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
	
}
