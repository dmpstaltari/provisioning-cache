package com.cablevision.provisioning.coliving.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cablevision.provisioning.coliving.dtos.ColivingOperationDTO;
import com.cablevision.provisioning.coliving.models.ColivingOperation;
import com.cablevision.provisioning.coliving.services.IColivingOperationService;

@RestController
@RequestMapping("/coliving/operation")
@Validated
public class ColivingOperationController {

	private final Logger log = LoggerFactory.getLogger(ColivingOperationController.class);

	@Autowired
	private IColivingOperationService colivingOperationService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<ColivingOperationDTO>> getAllColivingOperations() {
		log.debug("Getting all coliving operations");
		List<ColivingOperation> colivingOperations = colivingOperationService.getAllColivingOperations();
		return ResponseEntity.ok(colivingOperations.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ColivingOperationDTO> getColivingOperationById(@PathVariable("id") @NotBlank String colivingOperationId) {
		log.debug("Getting coliving operation with id: {}", colivingOperationId);
		return ResponseEntity.ok(this.convertToDto(colivingOperationService.getColivingOperationById(colivingOperationId)));
	}

	
	@GetMapping("/{provision}/{operation}/{option}")
	public ResponseEntity<ColivingOperationDTO> getColivingOperation(@PathVariable("provision") @NotBlank String provision, 
			@PathVariable("operation") @NotBlank String operation, 
			@PathVariable("option") @NotBlank String option) {
		log.debug("Getting coliving operation: {} - {} - {}", provision, operation, option);
		return ResponseEntity.ok(this.convertToDto(colivingOperationService.getColivingOperation(provision, operation, option)));
	}
	
	@PostMapping("/{provision}/{operation}/{option}/{action}")
	public ResponseEntity<ColivingOperationDTO> createColivingOperation(@PathVariable("provision") @NotBlank String provision, 
			@PathVariable("operation") @NotBlank String operation, 
			@PathVariable("option") @NotBlank String option,
			@PathVariable("action") @NotBlank String action) throws URISyntaxException {
		log.debug("Creating a new coliving operation: {} - {} - {} - {}", provision, operation, option, action);
		ColivingOperation colivingOperation = colivingOperationService.createColivingOperation(provision, operation, option, action);
		return ResponseEntity.created(new URI("/coliving/operation/" + colivingOperation.getId())).body(this.convertToDto(colivingOperation));
	}
	
	
	@PostMapping
	public ResponseEntity<ColivingOperationDTO> createColivingOperationBodyData(@RequestBody @Valid ColivingOperationDTO colivingOperationDto) throws URISyntaxException {
		log.debug("Creating a new coliving operation: {}", colivingOperationDto);
		ColivingOperation colivingOperation = colivingOperationService.createColivingOperation(colivingOperationDto.getProvision(),
				colivingOperationDto.getOperation(), colivingOperationDto.getOption(), colivingOperationDto.getAction());
		return ResponseEntity.created(new URI("/coliving/operation/" + colivingOperation.getId())).body(this.convertToDto(colivingOperation));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteColivingOperation(@PathVariable("id") @NotBlank String colivingOperationId) {
		log.debug("Deleting coliving operation with id: {}", colivingOperationId);
		colivingOperationService.deleteColivingOperation(colivingOperationId);
		return ResponseEntity.ok().build();
	}

	private ColivingOperationDTO convertToDto(ColivingOperation colivingOperation) {
		return modelMapper.map(colivingOperation, ColivingOperationDTO.class);
	}

}