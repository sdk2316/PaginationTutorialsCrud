package com.durgesh.exception;

import java.util.Date;


public class ErrorMessage {

	
	  private Date timestamp;
	  private String message;
	  private String description;
	  
	  
	  public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorMessage( Date timestamp, String message, String description) {
		   
		    this.timestamp = timestamp;
		    this.message = message;
		    this.description = description;
		  }
		
		  public Date getTimestamp() {
		    return timestamp;
		  }
		  public String getMessage() {
		    return message;
		  }
		  public String getDescription() {
		    return description;
		  }
		}