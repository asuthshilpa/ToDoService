package io.testservice.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.testservice.exception.ItemNotFoundException;
import io.testservice.model.Details;
import io.testservice.model.ErrorDetails;
import io.testservice.model.ToDoItemNotFoundError;
import io.testservice.model.ToDoItemValidationError;


@ControllerAdvice

public class ToDoExceptionHandler extends ResponseEntityExceptionHandler {
private final String VALIDATION_ERROR = "ValidationError";
private final String NOTFOUND_ERROR = "NotFoundError";
	
	  @Override
      protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		  
		  List<Details> details = new ArrayList<>();
			Details det = new Details();
			det.setMsg(ex.getMessage());
			det.setLocation("Body");
			det.setParam("");
			details.add(det);

			ToDoItemValidationError apierror = new ToDoItemValidationError(details, VALIDATION_ERROR);
			return new ResponseEntity<Object>(apierror, HttpStatus.BAD_REQUEST);
         
      }
	  @Override
      protected ResponseEntity<Object> handleMissingServletRequestParameter(
  			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request)  {
		  
		  List<Details> details = new ArrayList<>();
			Details det = new Details();
			det.setMsg(ex.getMessage());
			det.setLocation("Request Param");
			det.setParam("");
			details.add(det);

			ToDoItemValidationError apierror = new ToDoItemValidationError(details, VALIDATION_ERROR);
			return new ResponseEntity<Object>(apierror, HttpStatus.BAD_REQUEST);
         
      }
	  

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Details> details = new ArrayList<>();
		Details det = new Details();
		det.setMsg(ex.getMessage());
		det.setLocation(ex.getVariableName());
		det.setParam(ex.getParameter().getParameterName());
		details.add(det);

		ToDoItemValidationError apierror = new ToDoItemValidationError(details, VALIDATION_ERROR);
		return new ResponseEntity<Object>(apierror, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	 @ExceptionHandler(ItemNotFoundException.class)
	   protected ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException ex, HttpServletRequest request) {
		 List<ErrorDetails> detailsList = new ArrayList<>();
			
			detailsList.add(new ErrorDetails(ex.getMessage()));
			
			ToDoItemNotFoundError apierror = new ToDoItemNotFoundError(detailsList ,NOTFOUND_ERROR);
			 return new ResponseEntity<Object>(apierror, HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	   protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
		List<Details> details = new ArrayList<>();
		ex.getConstraintViolations().forEach(err ->
		{
			Details det = new Details();
			det.setMsg(err.getMessage());
			det.setLocation(err.getPropertyPath().toString());
			det.setParam(err.getExecutableParameters()[0].toString());
			det.setValue(err.getInvalidValue().toString());
			details.add(det);
		});
		
		ToDoItemValidationError apierror = new ToDoItemValidationError(details, VALIDATION_ERROR);
		return new ResponseEntity<Object>(apierror, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Details> details = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().stream().forEach(err -> {
			Details det = new Details();
			det.setMsg(err.getDefaultMessage());
			det.setLocation(err.getObjectName());
			det.setParam(err.getField());
			det.setValue((String) err.getRejectedValue());
			details.add(det);

		});
		ToDoItemValidationError apierror = new ToDoItemValidationError(details, VALIDATION_ERROR);
		return new ResponseEntity<Object>(apierror, HttpStatus.BAD_REQUEST);
	}

}