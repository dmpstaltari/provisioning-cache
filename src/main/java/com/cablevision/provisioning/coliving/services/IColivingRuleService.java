package com.cablevision.provisioning.coliving.services;

import java.util.List;

import com.cablevision.provisioning.coliving.models.ColivingRule;
import com.cablevision.provisioning.coliving.models.ColivingRule.Condition;

public interface IColivingRuleService {
   
    List<ColivingRule> getAllColivingRules();
	
    ColivingRule getColivingRuleById(String colivingRuleId);
	
    ColivingRule createColivingRule(String attribute, String value, Condition condition);

    void deleteColivingRule(String colivingRuleId);
  
}
