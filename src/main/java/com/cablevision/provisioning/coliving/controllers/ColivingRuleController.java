package com.cablevision.provisioning.coliving.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cablevision.provisioning.coliving.dtos.ColivingRuleDTO;
import com.cablevision.provisioning.coliving.models.ColivingRule;
import com.cablevision.provisioning.coliving.models.ColivingRule.Condition;
import com.cablevision.provisioning.coliving.services.IColivingRuleService;

@RestController
@RequestMapping("/coliving/rule")
@Validated
public class ColivingRuleController {

	private final Logger log = LoggerFactory.getLogger(ColivingRuleController.class);

	@Autowired
	private IColivingRuleService colivingRuleService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<ColivingRuleDTO>> getAllColivingRules() {
		log.debug("Getting all coliving rules");
		List<ColivingRule> colivingRules = colivingRuleService.getAllColivingRules();
		return ResponseEntity.ok(colivingRules.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ColivingRuleDTO> getColivingRuleById(@PathVariable("id") @NotBlank String colivingRuleId) {
		log.debug("Getting coliving rule with id: {}", colivingRuleId);
		return ResponseEntity.ok(this.convertToDto(colivingRuleService.getColivingRuleById(colivingRuleId)));
	}

	
	@PostMapping("/{attribute}/{condition}/{value}")
	public ResponseEntity<ColivingRuleDTO> createColivingRule(@PathVariable("attribute") @NotBlank String attribute, 
			@PathVariable("condition") @NotNull Condition condition, 
			@PathVariable("value") @NotBlank String value) throws URISyntaxException {
		log.debug("Creating a new coliving rule: {} {} {}", attribute, condition, value);
		ColivingRule colivingRule = colivingRuleService.createColivingRule(attribute, value, condition);
		return ResponseEntity.created(new URI("/coliving/rule/" + colivingRule.getId())).body(this.convertToDto(colivingRule));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteColivingRule(@PathVariable("id") @NotBlank String colivingRuleId) {
		log.debug("Deleting coliving rule with id: {}", colivingRuleId);
		colivingRuleService.deleteColivingRule(colivingRuleId);
		return ResponseEntity.ok().build();
	}

	private ColivingRuleDTO convertToDto(ColivingRule colivingRule) {
		return modelMapper.map(colivingRule, ColivingRuleDTO.class);
	}

}