package com.cablevision.provisioning.coliving.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Document(collection = "colivingoperations")
public class ColivingOperation {

	@Id
	private String id;
	
	@NonNull
	private String provision;

	@NonNull
	private String operation;

	@NonNull
	private String option;
	
	@NonNull
	private String action;
	
}
