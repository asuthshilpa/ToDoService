package io.testservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDoItem {

	@Id
	@GeneratedValue
	int id;
	String text;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	boolean isCompleted;
	String createdAt;
	

}
