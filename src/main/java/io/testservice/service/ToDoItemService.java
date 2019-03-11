package io.testservice.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.testservice.model.ToDoItem;
import io.testservice.model.ToDoPatchReq;
import io.testservice.repository.ToDoItemRepository;

@Service
public class ToDoItemService {

	@Autowired
	ToDoItemRepository todorepo;

	public ToDoItemService() {

	}

	
	// add question to database
	@Transactional
	public ToDoItem addToDoItem(String text) {

		ToDoItem item = new ToDoItem();
		item.setText(text);
		item.setIsCompleted(false);
		Instant timestamp = Instant.now();
		item.setCreatedAt(timestamp.toString());
	
		ToDoItem q = todorepo.save(item);
		return q;

	}
	
	public ToDoItem getItemAtId(int id) {
		Optional<ToDoItem> todoOpt = todorepo.findById(id);
		if (todoOpt.isPresent())
			return (ToDoItem) todoOpt.get();
		else
			return null;
	}
	
	
	public ToDoItem updateToDoAtId(int id, ToDoPatchReq req) {
		Optional<ToDoItem> todoOpt = todorepo.findById(id);
		if (todoOpt.isPresent()) {

			ToDoItem u = (ToDoItem) todoOpt.get();
			u.setText(req.getText());
			System.out.println("req.isCompleted()"+req.getIsCompleted());
			u.setIsCompleted(req.getIsCompleted());
			todorepo.save(u);
			return u;
		} else
			return null;
	}

}
