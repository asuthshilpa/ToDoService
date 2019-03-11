package io.testservice.model;

import java.util.List;

public class ToDoItemNotFoundError {
	

	 private String name;
	 private List<ErrorDetails> details;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ErrorDetails> getDetails() {
		return details;
	}
	public void setDetails(List<ErrorDetails> details) {
		this.details = details;
	}
	
	public ToDoItemNotFoundError(List<ErrorDetails> details,String name) {
       super();
       this.name = name;
       this.details = details;
   }
 

}

