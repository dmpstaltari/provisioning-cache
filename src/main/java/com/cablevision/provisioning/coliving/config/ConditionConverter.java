package com.cablevision.provisioning.coliving.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cablevision.provisioning.coliving.models.ColivingRule.Condition;

@Component
public class ConditionConverter implements Converter<String, Condition> {

	@Override
	public Condition convert(String value) {
		return Condition.findByName(value);
	}

}
