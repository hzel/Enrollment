/* HAZEL
 * WAS
 * HERE
 */


package com.orangeandbronze.interns.enrollment;

import java.util.HashSet;
import java.util.Set;

public class Teacher {
	
	private String facultyID;
	private Set<Section> sectionsHandled = new HashSet<Section>();
	
	public Teacher(){
		
	}
	
	public Teacher(String facultyID) {
		this.facultyID = facultyID;
	}
	
	@Override
	public String toString() {
		return facultyID;
	}
	
	/**
     * Adds a section to this teacher's set of sections handled.
     * 
     * @param section
     */
	public void addSectionsHandled(Section section) {
		sectionsHandled.add(section);
	}
	
	public Set<Section> getSectionsHandled() {
		return sectionsHandled;
	}
	
	/**
     * Checks if there is schedule conflict with the sections handled by 
     * this teacher.
     * 
     * @param schedule
     * @throws EnrollmentException
     */
	public void checkForTeacherScheduleConflict(Section section) throws EnrollmentException {
		if(hasTeacherConflictSchedule(section)){
			Section conflictedSection = getSectionWithScheduleConflict(section);
			throw new EnrollmentException("Cannot create new section because of teacher's schedule conflict with "+conflictedSection.toString()+" on "+section.getSchedule().toString());
		}
	}
	
	private boolean hasTeacherConflictSchedule(Section pendingSection) {
		Section sectionWithConflict = getSectionWithScheduleConflict(pendingSection);
		if (sectionWithConflict != null) {
			System.out.println(sectionWithConflict.toString());
			return true;
		}
		return false;
	}

	private Section getSectionWithScheduleConflict(Section pendingSection) {
		for (Section sectionHandled : sectionsHandled){
			if (sectionHandled.isConflictWith(pendingSection)){
				return sectionHandled;
			}
		}
		return null;
	}

}
