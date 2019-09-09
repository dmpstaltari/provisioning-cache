package com.cablevision.provisioning.coliving.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cablevision.provisioning.coliving.exceptions.ResourceNotFoundException;
import com.cablevision.provisioning.coliving.models.ColivingOperation;
import com.cablevision.provisioning.coliving.repositories.ColivingOperationsRepository;

@Service
public class ColivingOperationService implements IColivingOperationService {

	@Autowired
	private ColivingOperationsRepository colivingOperationRepository;

	@Override
	public List<ColivingOperation> getAllColivingOperations() {
		return colivingOperationRepository.findAll();
	}

	@Override
	public ColivingOperation getColivingOperationById(String colivingOperationId) {
		return colivingOperationRepository.findById(colivingOperationId).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public ColivingOperation getColivingOperation(String provision, String operation, String option) {
		return colivingOperationRepository.findByProvisionAndOperationAndOption(provision, operation, option).orElseThrow(ResourceNotFoundException::new);
	}
	
	
	@Override
	public ColivingOperation createColivingOperation(String provision, String operation, String option, String action) {
		ColivingOperation colivingOperation = new ColivingOperation(provision, operation, option, action);
		colivingOperation = colivingOperationRepository.save(colivingOperation);
		return colivingOperation;
	}
	
	@Override
	public void deleteColivingOperation(String colivingOperationId) {
		ColivingOperation colivingOperation = colivingOperationRepository.findById(colivingOperationId).orElseThrow(ResourceNotFoundException::new);
		colivingOperationRepository.delete(colivingOperation);
	}

}
