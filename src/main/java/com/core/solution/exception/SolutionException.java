package com.core.solution.exception;

public class SolutionException extends Exception {

	private static final long serialVersionUID = -1360070792360045565L;

	private SolutionData solutionData;
	
	public SolutionException(String message, SolutionData solutionData, Throwable e) {
		super(message, e);
		setDataException(solutionData);
	}
	
	public SolutionException(String message, SolutionData solutionData) {
		super(message);
		setDataException(solutionData);
	}

	public SolutionData getDataException() {
		return solutionData;
	}

	public void setDataException(SolutionData solutionData) {
		this.solutionData = solutionData;
	}
	
}
