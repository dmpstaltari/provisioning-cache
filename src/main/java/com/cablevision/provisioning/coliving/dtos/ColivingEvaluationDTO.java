package com.cablevision.provisioning.coliving.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ColivingEvaluationDTO {
	
	@NotBlank
	private String provision;

	@NotBlank
	private String operation;

	@NotBlank
	private String option;

	@Size(min=1)
	private List<ColivingDataDTO> data = new ArrayList<>();

}
