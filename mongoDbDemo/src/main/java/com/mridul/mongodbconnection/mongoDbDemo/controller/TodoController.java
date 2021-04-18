package com.mridul.mongodbconnection.mongoDbDemo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mridul.mongodbconnection.mongoDbDemo.model.TodoDTO;
import com.mridul.mongodbconnection.mongoDbDemo.repositery.TodoRepositery;


@RestController
public class TodoController {
	
	@Autowired
	private TodoRepositery todoRepo;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos(){
		List<TodoDTO> todos=todoRepo.findAll();
		if(todos.size()>0) {
			return new ResponseEntity<List<TodoDTO>>(todos,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("NO DATA AVILABLE",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/todos")
	public ResponseEntity<?> addTodos(@RequestBody TodoDTO todo){
		try {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
			return new ResponseEntity<TodoDTO>(todo,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getTodoById(@PathVariable("id") String id){
		Optional<TodoDTO> todoById=todoRepo.findById(id);
		if(todoById.isPresent()) {
			return new ResponseEntity<>(todoById.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Todo by id not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateTodoById(@PathVariable("id") String id,@RequestBody TodoDTO todoDto){
		Optional<TodoDTO> optional=todoRepo.findById(id);
		if(optional.isPresent()) {
			TodoDTO todoSave=optional.get();
			System.out.println("OPTIONAL"+todoSave);
			todoSave.setCompleted(todoDto.getCompleted()!=null? todoDto.getCompleted() : todoSave.getCompleted());
		    todoSave.setTodo(todoDto.getTodo()!=null ? todoDto.getTodo() : todoSave.getTodo());
		    todoSave.setDescription(todoDto.getDescription()!=null ? todoDto.getDescription() : todoSave.getDescription());
		    todoSave.setUpdatedDate(new Date(System.currentTimeMillis()));
		    todoRepo.save(todoSave);
		    return new ResponseEntity<>(todoRepo,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Todo with id not found"+id,HttpStatus.NOT_FOUND);
		}
	}

}
