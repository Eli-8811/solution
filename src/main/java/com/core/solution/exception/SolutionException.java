package com.core.solution.exception;

public class SolutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1360070792360045565L;

	private DataException dataException;
	
	public SolutionException(String message, DataException dataException, Throwable e) {
		super(message, e);
		setDataException(dataException);
	}
	
	public SolutionException(String message, DataException dataException) {
		super(message);
		setDataException(dataException);
	}

	public DataException getDataException() {
		return dataException;
	}

	public void setDataException(DataException dataException) {
		this.dataException = dataException;
	}
	
}
