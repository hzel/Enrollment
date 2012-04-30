package com.orangeandbronze.interns.enrollment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student {
	
	private String studentNumber;
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();

	public Student(){
		
	}
	
	public Student(String studentNumber) {
		verifyParameter(studentNumber);
		this.studentNumber = studentNumber;
	}
	
	private void verifyParameter(Object obj) throws IllegalArgumentException{
		if (obj == null) {
			throw new IllegalArgumentException("Null parameter");
		}
	}

	@Override
	public String toString() {
		return studentNumber;
	}
	
	/**
     * Adds an Enrollment to this student's list of enrollments
     * 
     * @param enrollment
     */
	public void addEnrollment(Enrollment enrollment) {
		this.enrollments.add(enrollment);
	}

	/**
     * Gets the prerequisite subjects not yet taken by this student 
     * when he attempts to enroll in a new subject.
     * 
     * @param     subject
     * @return    unmodifiable set of subjects
     */
	public Set<Subject> getPrereqsNotYetTaken(Subject subject) {
		Set<Section> previousSections = getSectionsFromPreviousEnrollments();
		
		Set<Subject> previousSubjectsTaken = new HashSet<Subject>();
		previousSubjectsTaken = getSubjectsTaken(previousSections);
		
		Set<Subject> prereqsNotYetTaken = new HashSet<Subject>();
		for (Subject prerequisite : subject.getPrerequisites()){
			if (!previousSubjectsTaken.contains(prerequisite)) {
				prereqsNotYetTaken.add(prerequisite);
			}
		}
		Set<Subject> unmodifiablePrerequisiteSet = getUnmodifiableSetOfSubjects(prereqsNotYetTaken);
		return unmodifiablePrerequisiteSet;
	}
	
	private Set<Subject> getUnmodifiableSetOfSubjects(Set<Subject> subjects){
		return Collections.unmodifiableSet(subjects);
	}

	private Set<Section> getSectionsFromPreviousEnrollments(){
		int previousEnrollmentsCount = enrollments.size()-1;
		Set<Section> previousSections = new HashSet<Section>();
		for (int i=0; i < previousEnrollmentsCount; i++){
			previousSections.addAll(enrollments.get(i).getSections());
		}
		return previousSections;
		
	}
	
	private Set<Subject> getSubjectsTaken(Set<Section> previousSections) {
		Set<Subject> previousSubjectsTaken = new HashSet<Subject>();
		for (Section previousSection : previousSections){
			previousSubjectsTaken.add(previousSection.getSubject());
		}
		Set<Subject> unmodifiableSubjectSet = getUnmodifiableSetOfSubjects(previousSubjectsTaken);
		return unmodifiableSubjectSet;
	}

}
