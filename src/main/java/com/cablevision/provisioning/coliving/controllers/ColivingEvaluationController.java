package com.cablevision.provisioning.coliving.controllers;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cablevision.provisioning.coliving.dtos.ColivingEvaluationDTO;
import com.cablevision.provisioning.coliving.dtos.ColivingEvaluationResponseDTO;
import com.cablevision.provisioning.coliving.exceptions.ResourceNotFoundException;
import com.cablevision.provisioning.coliving.models.ColivingOperation;
import com.cablevision.provisioning.coliving.services.IColivingOperationService;
import com.cablevision.provisioning.coliving.services.IColivingRuleService;

@RestController
@RequestMapping("/coliving/evaluation")
@Validated
public class ColivingEvaluationController {

	public static final String SOM = "SOM";
	public static final String OPENDOORS = "OPENDOORS";
	private final Logger log = LoggerFactory.getLogger(ColivingEvaluationController.class);

	@Autowired
	private IColivingOperationService colivingOperationService;

	@Autowired
	private IColivingRuleService colivingRuleService;
	
	
	@PostMapping
	public ResponseEntity<ColivingEvaluationResponseDTO> evaluateColiving(@RequestBody @Valid ColivingEvaluationDTO colivingEvaluationDto) throws URISyntaxException {
		log.info("Evaluating coliving operation: {}", colivingEvaluationDto);

		ColivingOperation colivingOperation;

		try {
			colivingOperation = colivingOperationService.getColivingOperation(colivingEvaluationDto.getProvision(), colivingEvaluationDto.getOperation(), colivingEvaluationDto.getOption());
		}catch (ResourceNotFoundException e) {
			log.debug("Activation system: {}", OPENDOORS);
			return ResponseEntity.ok(new ColivingEvaluationResponseDTO("OPENDOOR", "N/A"));
		}

		log.debug("Activation system: {}", SOM);
		return ResponseEntity.ok(new ColivingEvaluationResponseDTO(SOM, colivingOperation.getAction()));
	}

}