package com.cablevision.provisioning.coliving.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ColivingEvaluationResponseDTO {
	
	@NotBlank
	private String system;

	@NotBlank
	private String action;

}
