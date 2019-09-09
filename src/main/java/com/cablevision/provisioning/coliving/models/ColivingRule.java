package com.cablevision.provisioning.coliving.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Document(collection = "colivingrules")
public class ColivingRule {

	public enum Condition {
		EQUALS("igual"),
		NOT_EQUALS("distinto");

		private final String name;

		Condition(String name) {
			this.name = name;
		}

		@JsonValue
		@Override
		public String toString() {
			return name;
		}

		public static Condition findByName(String name) {
			for (Condition condition : Condition.values()) {
				if (condition.name.equals(name)) {
					return condition;
				}
			}
			return null;
		}

	}

	@Id
	private String id;

	@NonNull
	private String attribute;

	@NonNull
	private String value;

	@NonNull
	@JsonProperty("condicion")
	private Condition condition;

}
