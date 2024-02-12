package com.sunknowledge.dme.rcm.service.dto.common;

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
public class ServiceOutcome<T> implements Serializable {
	private static final long serialVersionUID = 2366194261510551608L;
	private T data;
	private Boolean outcome;
	private String message;
    Integer statusCode;
}
