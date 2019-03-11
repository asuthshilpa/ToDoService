package io.testservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ToDoPatchReq {
	@NotBlank
	@Size(min=1, max=50)
	String text;
	boolean isCompleted;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
