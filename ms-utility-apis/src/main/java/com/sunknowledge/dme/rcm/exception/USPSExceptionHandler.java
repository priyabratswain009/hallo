package com.sunknowledge.dme.rcm.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;

public class USPSExceptionHandler extends Exception {
	private static final long serialVersionUID = 1L;

	public USPSExceptionHandler() {

	}

	public USPSExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public USPSExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}

	public USPSExceptionHandler(String message) {
		super(message);
	}

	public USPSExceptionHandler(Throwable cause) {
		super(cause);
	}

	public static void processOnError(Exception e) {
		try {
			throw new ServletException(e);
		}
		catch(ServletException se) {
			Logger logger = Logger.getLogger(USPSExceptionHandler.class.getName());
			logger.log(Level.SEVERE, e.getMessage(), e);
			StringBuilder builder = new StringBuilder();
			for(StackTraceElement stackTrace : e.getStackTrace()) {
				builder.append(stackTrace);
				builder.append("\n");
			}
		}
	}
}
