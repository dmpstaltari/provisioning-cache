package com.cablevision.provisioning.coliving.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cablevision.provisioning.coliving.models.ColivingRule.Condition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColivingRuleDTO {
	
	private String id;
	
	@NotBlank
	private String attribute;

	@NotBlank
	private String value;

	@NotNull
	private Condition condition;

}
