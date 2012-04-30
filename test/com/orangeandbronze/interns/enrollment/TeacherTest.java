package com.orangeandbronze.interns.enrollment;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class TeacherTest {

	/**
	 * A teacher cannot teach two sections with the same schedule.
	 * 
	 * Test that shows a teacher cannot teach two sections with the same schedule.
	 * 
	 * @throws EnrollmentException
	 */
	@Test (expected = EnrollmentException.class)
	public void cannotTeachTwoSectionsWithSameScheduleinOneEnrollment () throws EnrollmentException{
		Teacher teacher =  new Teacher("T-2000-12345");

		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		@SuppressWarnings("unused")
		Section section1JazzDance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1JazzDance101");
		
		Subject swimming1 = new Subject(new HashSet<Subject>(), "swimming1");
		@SuppressWarnings("unused")
		Section section1Swimming1 = new Section(teacher, swimming1, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1Swimming1");
	}

	/**
	 * A teacher cannot teach two sections with the same schedule.
	 * 
	 * Test that shows a teacher can teach in two sections with same schedule if the sections are on different academic semesters.
	 * 
	 * @throws IllegalArgumentException
	 * @throws EnrollmentException
	 */
	@Test
	public void hasSameScheduleInDifferentAcademicSemesters() throws IllegalArgumentException, EnrollmentException {
		Teacher teacher =  new Teacher("T-2000-12345");
		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		Section section1JazzDance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1JazzDance101");
		
		Section section2JazzDance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.SECOND, "section2JazzDance101");

		assertFalse(section1JazzDance101.isConflictWith(section2JazzDance101));
	}
}
