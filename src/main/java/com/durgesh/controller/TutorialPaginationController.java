package com.durgesh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.model.Tutorial;
import com.durgesh.service.ITutorialService;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/tutorials")
public class TutorialPaginationController {
	
	@Autowired
	ITutorialService tutorialService;
	  @GetMapping("/pagination")
	  public ResponseEntity<Map<String, Object>> getAllTutorials(
	        @RequestParam(required = false) String title,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size
	      ) {
	    try {
	      List<Tutorial> tutorials = new ArrayList<Tutorial>();
	      Pageable paging = PageRequest.of(page, size);
	      
	      Page<Tutorial> pageTuts;
	      if (title == null)
	        pageTuts =tutorialService.getAllPage(paging);
	      else
	        pageTuts = tutorialService.getByTitleContaining(title, paging);
	      tutorials = pageTuts.getContent();
	      Map<String, Object> response = new HashMap<>();
	      response.put("tutorials", tutorials);
	      response.put("currentPage", pageTuts.getNumber());
	      response.put("totalItems", pageTuts.getTotalElements());
	      response.put("totalPages", pageTuts.getTotalPages());
	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/pagination/published")
	  public ResponseEntity<Map<String, Object>> findByPublished(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size
	      ) {
	    try {      
	      List<Tutorial> tutorials = new ArrayList<Tutorial>();
	      Pageable paging = PageRequest.of(page, size);
	      
	      Page<Tutorial> pageTuts = tutorialService.getByPublished(true, paging);
	      tutorials = pageTuts.getContent();
	            
	      Map<String, Object> response = new HashMap<>();
	      response.put("tutorials", tutorials);
	      response.put("currentPage", pageTuts.getNumber());
	      response.put("totalItems", pageTuts.getTotalElements());
	      response.put("totalPages", pageTuts.getTotalPages());
	      
	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
