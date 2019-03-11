package io.testservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ToDoReq {
	@NotBlank
	@Size(min=1, max=50)
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
