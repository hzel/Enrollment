package com.orangeandbronze.interns.enrollment;

/**
 * 
 * 
 * @author Team 10
 * 
 * Throw this exception when one of the following business rules is violated:
 * 	There are missing prerequisites.
 * 	A section has already been enrolled.
 * 	There is schedule conflict.
 *  Exceeds maximum load.
 *  Section is full.
 *  Has not reached minimum load.
 *  
 */
@SuppressWarnings("serial")
public class EnrollmentException extends Exception {

	public EnrollmentException(String message) {
		super(message);
	}
	
}
