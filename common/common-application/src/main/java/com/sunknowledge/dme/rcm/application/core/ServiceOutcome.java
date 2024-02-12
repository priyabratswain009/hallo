package com.sunknowledge.dme.rcm.application.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Bimal K Sahoo
 * @since 10/05/2022
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"data", "success", "message"})
public class ServiceOutcome<T> implements Serializable {
	private static final long serialVersionUID = 2366194261510551608L;
	private T data;
	private Boolean outcome;
	private String message;
	private String statusCode;

	public ServiceOutcome(T data, Boolean outcome, String message) {
		this.data = data;
		this.outcome = outcome;
		this.message = message;
	}
}
