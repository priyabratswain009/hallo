package com.sunknowledge.dme.rcm.core;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * @author Bimal K Sahoo
 * @since 10/05/2022
 *
 */
@Data
public class ServiceOutcome<T> implements Serializable {
	private static final long serialVersionUID = 2366194261510551608L;
	private T data;
	private Boolean outcome;
	private String message;
}
