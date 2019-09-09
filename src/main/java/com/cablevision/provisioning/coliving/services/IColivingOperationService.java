package com.cablevision.provisioning.coliving.services;

import java.util.List;

import com.cablevision.provisioning.coliving.models.ColivingOperation;

public interface IColivingOperationService {
   
    List<ColivingOperation> getAllColivingOperations();
	
    ColivingOperation getColivingOperation(String provision, String operation, String option);
    
    ColivingOperation getColivingOperationById(String colivingOperationId);
	
    ColivingOperation createColivingOperation(String provision, String operation, String option, String action);

    void deleteColivingOperation(String colivingOperationId);
  
}
