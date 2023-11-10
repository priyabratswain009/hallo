package com.sunknowledge.dme.rcm.domain.elligibility;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ServiceOutcome<T> implements Serializable {
	private static final long serialVersionUID = 2366194261510551608L;
	private T data;
	private List<T> dataList;
	private Boolean outcome;
	private String message;
}
