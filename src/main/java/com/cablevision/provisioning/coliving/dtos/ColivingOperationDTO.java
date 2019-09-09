package com.cablevision.provisioning.coliving.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColivingOperationDTO {
	
	private String id;
	
	@NotBlank
	private String provision;

	@NotBlank
	private String operation;

	@NotBlank
	private String option;

	@NotBlank
	private String action;

}
