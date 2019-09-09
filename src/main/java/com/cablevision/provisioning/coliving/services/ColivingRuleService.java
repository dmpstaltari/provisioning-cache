package com.cablevision.provisioning.coliving.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cablevision.provisioning.coliving.exceptions.ResourceNotFoundException;
import com.cablevision.provisioning.coliving.models.ColivingRule;
import com.cablevision.provisioning.coliving.models.ColivingRule.Condition;
import com.cablevision.provisioning.coliving.repositories.ColivingRulesRepository;

@Service
public class ColivingRuleService implements IColivingRuleService {

	@Autowired
	private ColivingRulesRepository colivingRuleRepository;

	@Override
	public List<ColivingRule> getAllColivingRules() {
		return colivingRuleRepository.findAll();
	}

	@Override
	public ColivingRule getColivingRuleById(String colivingRuleId) {
		return colivingRuleRepository.findById(colivingRuleId).orElseThrow(ResourceNotFoundException::new);
	}
	
	@Override
	public ColivingRule createColivingRule(String attribute, String value, Condition condition) {
		ColivingRule colivingRule = new ColivingRule(attribute, value, condition);
		colivingRule = colivingRuleRepository.save(colivingRule);
		return colivingRule;
	}
	
	@Override
	public void deleteColivingRule(String colivingRuleId) {
		ColivingRule colivingRule = colivingRuleRepository.findById(colivingRuleId).orElseThrow(ResourceNotFoundException::new);
		colivingRuleRepository.delete(colivingRule);
	}

}
