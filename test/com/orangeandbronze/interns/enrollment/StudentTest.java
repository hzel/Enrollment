package com.orangeandbronze.interns.enrollment;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {

	private Subject folkDance101;
	private Student student;
	private Enrollment year1sem1;
	private Section section1FolkDance101;
	private Schedule sched_WED_SAT_0830_1000;
	private Teacher teacher;

	@Before
	public void setUp() throws EnrollmentException {
		student =  new Student("2009-28185");
		teacher = new Teacher("T-2009-18152");
		folkDance101 = new Subject(new HashSet<Subject>(), "FolkDance101");
		sched_WED_SAT_0830_1000 = new Schedule(Day.WED_SAT, TimeSlot.TS_0830_1000);
		section1FolkDance101 = new Section(teacher, folkDance101, sched_WED_SAT_0830_1000, 2010, Semester.FIRST, "section1FolkDance101");
		year1sem1 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem1");
		student.addEnrollment(year1sem1);
		year1sem1.addSection(section1FolkDance101);
	}

	/**
	 * Test verifies that student has taken the required prerequisites from different enrollments.
	 * 
	 * @throws EnrollmentException
	 */
	@Test
	public void studentHasTakenPrereqs() throws EnrollmentException {
		HashSet<Subject> prereqSubjects = new HashSet<Subject>();
		Subject math101 = new Subject(new HashSet<Subject>(), "Math101");
		Subject math102 = new Subject(new HashSet<Subject>(), "Math102");
		prereqSubjects.add(math101);
		prereqSubjects.add(math102);

		Enrollment year1sem1 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem1");
		Schedule sched_WED_SAT_1000_1130 = new Schedule(Day.WED_SAT, TimeSlot.TS_1000_1130);
		Section section1math101 = new Section(teacher, math101, sched_WED_SAT_1000_1130, 2010, Semester.FIRST, "section1math101");
		year1sem1.addSection(section1math101);
		student.addEnrollment(year1sem1);
		
		Enrollment year1sem2 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem2");
		Schedule sched_WED_SAT_1130_1300 = new Schedule(Day.WED_SAT, TimeSlot.TS_1130_1300);
		Section section1math102 = new Section(teacher, math102, sched_WED_SAT_1130_1300, 2010, Semester.FIRST, "section1math102");
		year1sem2.addSection(section1math102);
		student.addEnrollment(year1sem2);
		
		Enrollment year2sem1 = new Enrollment(student, 2, StudentType.UNDERGRAD, Scholarship.NONE, "year2sem1");
		Subject physics1 = new Subject(prereqSubjects, "physics1");
		student.addEnrollment(year2sem1);
		
		Set<Subject> missingPrerequisites = year2sem1.getMissingPrereqs(physics1);
		assertTrue(missingPrerequisites.isEmpty());

	}

	/**
	 * Student cannot enroll in a section if he/she has not taken the prerequisite.
	 *
	 * Test for when the student attempts to enroll in a section but is missing a prerequisite subject
	 * 
	 * @throws EnrollmentException
	 */
	@Test (expected = EnrollmentException.class)
	public void studentHasMissingPrereq() throws EnrollmentException {
		Set<Subject> prereqSubjects = new HashSet<Subject>();
		Subject math101 = new Subject(new HashSet<Subject>(), "Math101");
		Subject math102 = new Subject(new HashSet<Subject>(), "Math102");
		prereqSubjects.add(math101);
		prereqSubjects.add(math102);

		Enrollment year1sem1 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem1");
		Schedule sched_WED_SAT_1000_1130 = new Schedule(Day.WED_SAT, TimeSlot.TS_1000_1130);
		Section section1math101 = new Section(teacher, math101, sched_WED_SAT_1000_1130, 2010, Semester.FIRST, "section1math101");
		year1sem1.addSection(section1math101);
		student.addEnrollment(year1sem1);

		Enrollment year1sem2 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem2");
		Subject physics1 = new Subject(prereqSubjects, "physics1");
		Schedule sched_WED_SAT_1130_1300 = new Schedule(Day.WED_SAT, TimeSlot.TS_1130_1300);
		Section section1_physics1 = new Section(teacher, physics1, sched_WED_SAT_1130_1300, 2010, Semester.FIRST, "section2_physics1");
		student.addEnrollment(year1sem2);
		year1sem2.addSection(section1_physics1);
	}

	/**
	 * System verifies that student has taken the required prerequisite(s).
	 * 
	 * Test verifies that student has taken the required prerequisites from one enrollment.
	 * @throws EnrollmentException
	 */
	@Test
	public void studentHasTakenPrereqsInTheSamePreviousSem() throws EnrollmentException {
		HashSet<Subject> prereqSubjects = new HashSet<Subject>();
		Subject math101 = new Subject(new HashSet<Subject>(), "Math101");
		Subject math102 = new Subject(new HashSet<Subject>(), "Math102");
		prereqSubjects.add(math101);
		prereqSubjects.add(math102);

		Enrollment year1sem1 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem1");		
		Schedule sched_MON_THU_1000_1130 = new Schedule(Day.MON_THU, TimeSlot.TS_1000_1130);
		Section section1math101 = new Section(teacher, math101, sched_MON_THU_1000_1130, 2010, Semester.FIRST, "section1math101");
		year1sem1.addSection(section1math101);

		Schedule sched_WED_SAT_1000_1130 = new Schedule(Day.WED_SAT, TimeSlot.TS_1000_1130);
		Section section1math102 = new Section(teacher, math102, sched_WED_SAT_1000_1130, 2010, Semester.FIRST, "section1math102");
		year1sem1.addSection(section1math102);
		student.addEnrollment(year1sem1);

		Enrollment year1sem2 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem2");
		student.addEnrollment(year1sem2);
		Subject physics1 = new Subject(prereqSubjects, "physics1");
		Set<Subject> missingPrerequisites = year1sem2.getMissingPrereqs(physics1);
		assertTrue(missingPrerequisites.isEmpty());
	}

	/**
	 * Student cannot enroll in two sections at the same time.
	 * 
	 * Test that shows a student cannot enroll in the same section more than once in the same semester.
	 * 
	 * @throws EnrollmentException
	 */
	@Test (expected = EnrollmentException.class)
	public void studentCannotEnrollInSameSectionInSameSem() throws EnrollmentException {
		year1sem1.addSection(section1FolkDance101);
	}

	/**
	 * System adds section to student’s list of sections.
	 * 
	 * Add an enrolled section to the student's list of sections.
	 */
	@Test
	public void addSectionToStudentsListOfSections() {
		Set<Section> sections = year1sem1.getSections();
		assertTrue(sections.contains(section1FolkDance101));
	}

}
