package com.orangeandbronze.interns.enrollment;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class SectionTest {

	private Schedule sched_MON_THU_0830_1000;
	private Subject jazzDance101;
	private Teacher teacher;
	private Section section1JazzDance101;
	private Enrollment year2sem1;
	
	@Before
	public void setUp() throws EnrollmentException{
		sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		teacher = new Teacher("T-2010-83829");
		section1JazzDance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1JazzDance101");	

		for(int i=1; i<=39; i++){
			year2sem1 = new Enrollment(new Student(Integer.toString(i)), 2, StudentType.UNDERGRAD, Scholarship.NONE, "year2sem1");
			year2sem1.addSection(section1JazzDance101);
		}
	}
	
	/**
	 * Passes a null for teacher value.
	 * 
	 * @throws EnrollmentException 
	 * @throws IllegalArgumentException 
	 * 
	 * */
	@Test (expected = IllegalArgumentException.class)
	public void nullTeacher() throws IllegalArgumentException, EnrollmentException {
		section1JazzDance101 = new Section(null, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1JazzDance101");
	}
	
	/**
	 * 
	 * Test that shows that a section has schedule conflict with another section.
	 * 
	 * @throws IllegalArgumentException
	 * @throws EnrollmentException
	 */
	@Test
	public void hasScheduleConflictWithAnotherSection() throws IllegalArgumentException, EnrollmentException {
		Teacher teacher2 = new Teacher("T-2010-83820");
		Section section2JazzDance101 = new Section(teacher2, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section2JazzDance101");	
		assertTrue(section1JazzDance101.isConflictWith(section2JazzDance101));
	}
	
	/**
	 * A section can accommodate a maximum of forty (40) students.
	 * 
	 * Enroll 40 students in a class
	 * 
	 * @throws EnrollmentException 
	 * */
	@Test
	public void sectionCanAccommodateMaximumFortyStudents() throws EnrollmentException {
		year2sem1 = new Enrollment(new Student("40"), 2, StudentType.UNDERGRAD, Scholarship.NONE, "year2sem1");
		year2sem1.addSection(section1JazzDance101);
		assertTrue(section1JazzDance101.isFull());
	}

	/**
	 * A section can accommodate a maximum of forty (40) students.
	 * 
	 * Attempt to enroll 41 students
	 * 
	 * @throws EnrollmentException 
	 * */
	@Test (expected = EnrollmentException.class)
	public void sectionCannotAccommodateMoreThanFortyStudents() throws EnrollmentException {
		year2sem1 = new Enrollment(new Student("40"), 2, StudentType.UNDERGRAD, Scholarship.NONE, "year2sem1");
		year2sem1.addSection(section1JazzDance101);
		year2sem1 = new Enrollment(new Student("41"), 2, StudentType.UNDERGRAD, Scholarship.NONE, "year2sem1");
		year2sem1.addSection(section1JazzDance101);
	}
	
	
	
}
