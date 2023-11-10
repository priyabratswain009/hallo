package com.sunknowledge.dme.rcm.domain.abn;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Bimal K Sahoo
 * @since 10/05/2022
 *
 */
@Data
public class SettingsOutput<T> implements Serializable {
	private static final long serialVersionUID = 2366194261510551608L;
	private T data;
	private Boolean status;
	private String message;
}
