package com.cablevision.provisioning.coliving.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cablevision.provisioning.coliving.exceptions.ResourceNotFoundException;
import com.cablevision.provisioning.coliving.models.ColivingOperation;
import com.cablevision.provisioning.coliving.repositories.ColivingOperationsRepository;

@RestController
@RequestMapping("/operations")
public class OperationController {

	@Autowired
	private ColivingOperationsRepository repository;

	@GetMapping
	public List<ColivingOperation> getAll() {
	  return repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ColivingOperation add(@Valid @RequestBody ColivingOperation planet) {
	  return repository.save(planet);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void delete(@PathVariable String id) {  
	    ColivingOperation planet = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
	    repository.delete(planet);
	}
	
}
