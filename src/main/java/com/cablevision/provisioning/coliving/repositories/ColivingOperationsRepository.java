package com.cablevision.provisioning.coliving.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cablevision.provisioning.coliving.models.ColivingOperation;

public interface ColivingOperationsRepository extends MongoRepository<ColivingOperation, String> {

	@Query("{'provision' : ?0, 'operation' : ?1, 'option' : ?2}")
    Optional<ColivingOperation> findByProvisionAndOperationAndOption(String provision, String operation, String option);
	
}
