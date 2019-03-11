package io.testservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.testservice.model.ToDoItem;
@Repository
public interface ToDoItemRepository extends CrudRepository<ToDoItem, Integer>{

}
