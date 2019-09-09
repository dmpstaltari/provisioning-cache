package com.cablevision.provisioning.coliving.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ColivingDataDTO {

	@NotBlank
	private String attribute;
	
	@NotBlank
	private String value;
}
