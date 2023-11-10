package com.sunknowledge.dme.rcm.domain.core;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * @author Bimal K Sahoo
 * @since 10/05/2022
 *
 */
public class ServiceOutcome<T> implements Serializable {
	private static final long serialVersionUID = 2366194261510551608L;
	private T data;
	private List<T> dataList;
	private Boolean outcome;
	private String message;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public Boolean getOutcome() {
		return outcome;
	}
	public void setOutcome(Boolean outcome) {
		this.outcome = outcome;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
