package io.testservice.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.testservice.exception.ItemNotFoundException;
import io.testservice.model.BalanceTestResult;
import io.testservice.model.ToDoItem;
import io.testservice.model.ToDoPatchReq;
import io.testservice.model.ToDoReq;
import io.testservice.service.ToDoItemService;
import io.testservice.utility.AGUtil;

@RestController
@Validated
public class AppControler {

	@Autowired
	ToDoItemService service;

	@RequestMapping(value = "/todo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> ToDoItemAddRequest(@Valid @RequestBody(required=true) ToDoReq request) throws Exception {

		return new ResponseEntity<Object>(service.addToDoItem(request.getText()), HttpStatus.CREATED);

	}

	@RequestMapping(value = {"/todo/{id}", "/todo/"}, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getToDo(@PathVariable(name="id",required=true) int id) throws Exception {

		ToDoItem toDoItem = service.getItemAtId(id);
		if (toDoItem != null) {

			return new ResponseEntity<Object>(toDoItem, HttpStatus.OK);
		} else {

			throw new ItemNotFoundException("Item with " + id + " not found");

		}

	}

	@RequestMapping(value = {"/todo/{id}", "/todo/"}, method = RequestMethod.PATCH, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> updateToDo(@PathVariable(name="id",required=true)  int id, @Valid @RequestBody(required=true) ToDoPatchReq request)
			throws Exception {

		ToDoItem toDoItem = service.getItemAtId(id);
		if (toDoItem != null) {

			toDoItem = service.updateToDoAtId(id, request);

			return new ResponseEntity<Object>(toDoItem, HttpStatus.OK);
		} else {

			throw new ItemNotFoundException("Item with " + id + " not found");

		}

	}

	@RequestMapping(value = "/tasks/validateBrackets", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> validateBrackets(
			@RequestParam(required = true) @Size(max = 100, min = 1) String input) throws Exception {

		AGUtil util = new AGUtil();
		boolean flag = util.isBalanced(input);

		BalanceTestResult result = new BalanceTestResult(input, flag);
		return new ResponseEntity<Object>(result, HttpStatus.OK);

	}

}
