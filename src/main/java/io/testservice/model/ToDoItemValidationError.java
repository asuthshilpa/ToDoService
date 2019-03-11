package io.testservice.model;

import java.util.List;

public class ToDoItemValidationError {
	 private String name;
	 private List<Details> details;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Details> getDetails() {
		return details;
	}
	public void setDetails(List<Details> details) {
		this.details = details;
	}
	
	public ToDoItemValidationError(List<Details> details,String name) {
        super();
        this.name = name;
        this.details = details;
    }
}
