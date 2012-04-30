//3rrrrrrd changeessssssss :))

package com.orangeandbronze.interns.enrollment;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Enrollment {

	private Student student;
	private Set<Section> sections = new HashSet<Section>();
	private Scholarship scholarshipStatus = Scholarship.NONE;
	private BigDecimal miscFees = new BigDecimal("2000");
	private StudentType studentType = StudentType.UNDERGRAD;
	private int year;
	private int academicYear;
	private Semester semester;
	private String enrollmentID;
	
	public Enrollment(){
		
	}
	
	public Enrollment(Student student, int year, StudentType studentType, Scholarship scholarshipStatus, String enrollmentID) {
		verifyParameter(student);
        verifyParameter(year);
        verifyParameter(studentType);
        verifyParameter(enrollmentID);
		this.student = student;
		this.year = year;
		this.studentType = studentType;
		this.scholarshipStatus = scholarshipStatus;
		this.enrollmentID = enrollmentID;
	}

	private void verifyParameter(Object obj) throws IllegalArgumentException{
		if (obj == null) {
			throw new IllegalArgumentException("Null parameter");
		}
	}
	
	@Override
	public String toString() {
		return enrollmentID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((enrollmentID == null) ? 0 : enrollmentID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Enrollment)) {
			return false;
		}
		Enrollment other = (Enrollment) obj;
		if (enrollmentID == null) {
			if (other.enrollmentID != null) {
				return false;
			}
		} else if (!enrollmentID.equals(other.enrollmentID)) {
			return false;
		}
		return true;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	/**
	 * Adds a section to this enrollment's set of sections 
	 * @param section
	 * @throws EnrollmentException
	 */
	public void addSection(Section section) throws EnrollmentException {
		if(checkForMissingPrerequisites(section.getSubject())) {
			Set<Subject> missingPrereqs = this.getMissingPrereqs(section.getSubject());
			String missingPrereqsString = "";
			if(!missingPrereqs.isEmpty()){
				for(Subject missingSubject : missingPrereqs){
					missingPrereqsString += " "+ missingSubject.toString();
				}
			}
			throw new EnrollmentException("Missing prerequisites:" + missingPrereqsString);
		}
		if(checkIfAlreadyEnrolledInSection(section)){
			throw new EnrollmentException("Student is already currently enrolled in: " + section.toString());
		}
		if(checkForScheduleConflicts(section)){
			Section sectionWithScheduleConflict = getSectionWithConflict(section);
			throw new EnrollmentException("Cannot enroll in "+section.toString()+" because of conflict with " + sectionWithScheduleConflict.toString());
		}
		if(checkIfExceedsMaximumLoad(section)){
			throw new EnrollmentException("Cannot enroll in "+section.toString()+" because load will exceed maximum load of " + this.computeMaxLoad() +" units");
		}
		if(section.isFull()){
			throw new EnrollmentException("Cannot enroll in "+section.toString()+" because it is already full");
		}
		section.addEnrollee(this);
		sections.add(section);
	}

	private boolean checkForMissingPrerequisites(Subject subject) throws EnrollmentException{
		Set<Subject> missingPrerequisites = getMissingPrereqs(subject);
		if(!missingPrerequisites.isEmpty()){
			return true;
		}
		return false;
	}

	private boolean checkIfAlreadyEnrolledInSection(Section section) throws EnrollmentException{
		return this.sections.contains(section);
	}

	public boolean checkForScheduleConflicts(Section section) {
		return getSectionWithConflict(section) != null;
	}
	
	private Section getSectionWithConflict(Section section) {
		for (Section sectionWithPossibleScheduleConflict : sections){
			if(section.isConflictWith(sectionWithPossibleScheduleConflict)){
				return sectionWithPossibleScheduleConflict;
			}
		}
		return null;
	}

	private boolean checkIfExceedsMaximumLoad(Section section) throws EnrollmentException{
		int possibleLoad = this.computeCurrentLoad() + 3;
		return hasExceededMaxmiumLoad(possibleLoad);
	}
	
	private boolean hasExceededMaxmiumLoad(int currentLoad) {
		int maxLoad = computeMaxLoad();
		return (currentLoad > maxLoad);
	}

	public Set<Section> getSections() {
		Set<Section> unmodifiableSections = Collections.unmodifiableSet(sections);
		return unmodifiableSections;
	}

	
	/**
	 * Returns the prerequisites not yet taken needed for a subject  
	 * @param subject
	 * @return prereqsNotYetTaken set of Subjects not yet taken
	 */
	public Set<Subject> getMissingPrereqs(Subject subject) {
		Set<Subject> prereqsNotYetTaken = student.getPrereqsNotYetTaken(subject);
		return prereqsNotYetTaken;
	}

	/**
	 * Returns the section that is has a schedule conflict with an attempted section to enroll in.
	 * @param section
	 * @return returns an enrolled section with schedule conflict with pending section 
	 */
	public Section getSectionWithScheduleConflict(Section section) {
		for (Section sectionEnrolled : sections){
			if (sectionEnrolled.getSchedule().equals(section)){
				return sectionEnrolled;
			}
		}
		return null;
	}

	/**
	 * Computes for and returns the number of units enrolled for this enrollment
	 * @return currentLoad number of units enrolled in for this enrollment
	 */
	public int computeCurrentLoad() {
		int currentLoad = 0; 
		for (Section oneSection : this.getSections()) {
			currentLoad += oneSection.getSubject().getUnits();
		}
		return currentLoad;
	}

	/**
	 * 
	 * @return	Minimum load based on year level
	 */
	public int computeMinLoad() {
		int firstYrMinLoad = 15;
		int secondthirdYrMinLoad = 18;
		int fourthYrMinLoad = 0;

		if (year == 1)
			return firstYrMinLoad;
		else if (year == 2 || year == 3)
			return secondthirdYrMinLoad;
		else return fourthYrMinLoad;
	}

	private int computeMaxLoad() {
		int firstYrMaxLoad = 18;
		int secondthirdYrMaxLoad = 24;
		int fourthYrMaxLoad = 21;

		if (year == 1)
			return firstYrMaxLoad;
		else if (year == 2 || year == 3)
			return secondthirdYrMaxLoad;
		else return fourthYrMaxLoad;
	}

	private boolean hasReachedMinimumLoad() {
		int currentLoad = this.computeCurrentLoad();
		return (currentLoad >= computeMinLoad());
	}

	/**
	 * Computes and returns the tuition fee for this enrollment if minimum load is reached
	 * 	throws an EnrollmentException if current load is less than minimum load 
	 * @return netTuitionFee net value of tuition fee for this enrollment
	 * @throws EnrollmentException
	 */
	public BigDecimal computeTuitionFee() throws EnrollmentException{
		checkIfMinimumLoadReached();
		int subjectsFeeWithoutDiscount = sections.size() * getSubjectsFee();
		BigDecimal discountedSubjectsFee = computeDiscountedSubjectsFee(subjectsFeeWithoutDiscount);
		BigDecimal netTuitionFee = discountedSubjectsFee.add(miscFees);
		return netTuitionFee;
	}

	private void checkIfMinimumLoadReached() throws EnrollmentException{
		if (!hasReachedMinimumLoad()){
			throw new EnrollmentException("Cannot compute for tuition fee because minimum load of " + this.computeMinLoad() + 
					"was not reached. Your current load enrolled: " + this.computeCurrentLoad());
		}
	}

	private BigDecimal computeDiscountedSubjectsFee(int subjectsFee) {
		BigDecimal subjectsFeeToBeDiscounted = new BigDecimal(subjectsFee);
		BigDecimal discountedSubjectsFee;
		String halfScholar = "HALF";
		String fullScholar = "FULL";

		if (halfScholar.compareToIgnoreCase(scholarshipStatus.toString()) == 0) {
			discountedSubjectsFee = subjectsFeeToBeDiscounted.divide(new BigDecimal("2"));
		}
		else if (fullScholar.compareToIgnoreCase(scholarshipStatus.toString()) == 0) {
			discountedSubjectsFee = new BigDecimal("0");
		}
		else {
			discountedSubjectsFee = subjectsFeeToBeDiscounted;
		}
		return discountedSubjectsFee;
	}

	private int getSubjectsFee() {
		return studentType.getSubjectFee();
	}
}
