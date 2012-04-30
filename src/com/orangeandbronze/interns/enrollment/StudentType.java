package com.orangeandbronze.interns.enrollment;

public enum StudentType {
	UNDERGRAD(2000), GRAD(4000);
	
	private final int subjectFee;
    
    StudentType(int subjectFee) { 
    	this.subjectFee = subjectFee; 
    }
    
    public int getSubjectFee() { 
    	return subjectFee; 
    }
}
