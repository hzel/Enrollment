package com.orangeandbronze.interns.enrollment;

import java.util.ArrayList;
import java.util.List;

public class Section {

	@SuppressWarnings("unused")
	private Teacher teacher;
	private Subject subject;
	private Schedule schedule = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
	private String sectionID;
	private int maxNumberOfStudentsInClass = 40;
	private int academicYear;
	private Semester semester;
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();
	
	public Section (){
		
	}
		
	public Section (Teacher teacher, Subject subject, Schedule schedule, int academicYear, Semester semester, String sectionID) throws IllegalArgumentException, EnrollmentException {
		verifyParameter(teacher);
		verifyParameter(subject);
		verifyParameter(schedule);
		verifyParameter(sectionID);
		
		this.schedule = schedule;
		this.semester = semester;
		this.academicYear = academicYear;
		teacher.checkForTeacherScheduleConflict(this);
		
		this.teacher = teacher;
		this.subject = subject;
		this.sectionID = sectionID;
		teacher.addSectionsHandled(this);
	}

	private void verifyParameter(Object obj) throws IllegalArgumentException{
		if (obj == null) {
			throw new IllegalArgumentException("Null parameter");
		}
	}

	@Override
	public String toString() {
		return subject.toString() + " on " + schedule.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sectionID == null) ? 0 : sectionID.hashCode());
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
		if (!(obj instanceof Section)) {
			return false;
		}
		Section other = (Section) obj;
		if (sectionID == null) {
			if (other.sectionID != null) {
				return false;
			}
		} else if (!sectionID.equals(other.sectionID)) {
			return false;
		}
		return true;
	}

	/**
     * Checks if the section is full.
     * 
     * @return     true if number of students enrolled in class is equal to
     *             max number of students allowed.
     */
	public boolean isFull() {
		return enrollments.size() == maxNumberOfStudentsInClass;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public Subject getSubject() {
		return subject;
	}

	/**
     * Increments the number of students enrolled by 1.
     */
	public void addEnrollee(Enrollment enrollment){
		enrollments.add(enrollment);
	}

	/**
	 * Checks if there is schedule conflict with another section.
	 * 
	 * @param section
	 * @return
	 */
	public boolean isConflictWith(Section section) {
		return  (isSameSemester(section) && isSameAcademicYear(section) && isSameSchedule(section));
	}

	private boolean isSameSemester(Section section) {
		return semester.equals(section.getSemester());
	}
	
	private boolean isSameAcademicYear(Section section) {
		return academicYear == section.getAcademicYear();
	}
	
	private boolean isSameSchedule(Section section) {
		return schedule.equals(section.getSchedule());
	}
	
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public Semester getSemester() {
		return semester;
	}
	
	
}
