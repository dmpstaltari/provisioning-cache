package com.cablevision.provisioning.coliving.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cablevision.provisioning.coliving.models.ColivingRule;

public interface ColivingRulesRepository extends MongoRepository<ColivingRule, String> {
	
}
