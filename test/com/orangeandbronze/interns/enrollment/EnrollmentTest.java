package com.orangeandbronze.interns.enrollment;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class EnrollmentTest {

	private Section section1Folkdance101;
	private Enrollment year1sem1;
	private Schedule sched_WED_SAT_0830_1000;
	private Student student;
	private Teacher teacher;

	/**
	 * Enrolls a student in folkdance101 for AY2010 first semester
	 * 
	 * @throws EnrollmentException
	 */
	@Before
	public void setUp() throws EnrollmentException{
		student = new Student("2009-28185");
		teacher = new Teacher("T-2009-28185");
		Subject folkDance101 = new Subject(new HashSet<Subject>(), "FolkDance101");
		sched_WED_SAT_0830_1000 = new Schedule(Day.WED_SAT, TimeSlot.TS_0830_1000);
		section1Folkdance101 = new Section(teacher, folkDance101, sched_WED_SAT_0830_1000, 2010, Semester.FIRST, "section1Folkdance101");
		year1sem1 = new Enrollment(student, 1, StudentType.UNDERGRAD, Scholarship.NONE, "year1sem1");
		student.addEnrollment(year1sem1);
		year1sem1.addSection(section1Folkdance101);
	}

	/**
	 * Student enrolls in a section.
	 */
	@Test
	public void enrollAStudent(){
		Set<Section> sections = year1sem1.getSections();
		assertTrue(sections.contains(section1Folkdance101));
	}

	/**
	 *  System verifies no schedule conflicts.
	 *  
	 * @throws EnrollmentException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void hasNoScheduleConflict() throws IllegalArgumentException, EnrollmentException {
		Teacher teacher2 = new Teacher("T-2010-83829");
		Subject streetDance101 = new Subject(new HashSet<Subject>(), "StreetDance101");
		Schedule sched_WED_SAT_1000_1130 = new Schedule(Day.WED_SAT, TimeSlot.TS_1000_1130);
		Section section1StreetDance101 =  new Section(teacher2, streetDance101, sched_WED_SAT_1000_1130, 2010, Semester.FIRST, "section1StreetDance101");
		year1sem1.addSection(section1StreetDance101);
		assertFalse(section1StreetDance101.isConflictWith(section1Folkdance101));
	}
	
	/**
	 * Tests for schedule conflict and identifies the sections in conflict
	 *
	 * @throws EnrollmentException
	 * */
	@Test (expected = EnrollmentException.class)
	public void sectionHasScheduleConflict() throws EnrollmentException{
		Teacher teacher2 = new Teacher("T-2010-83829");
		Subject streetDance101 = new Subject(new HashSet<Subject>(), "StreetDance101");
		Section section1StreetDance101 =  new Section(teacher2, streetDance101, sched_WED_SAT_0830_1000, 2010, Semester.FIRST, "section1StreetDance101");
		year1sem1.addSection(section1StreetDance101);
	}

	/**
	 * A student may not exceed maximum enrollment load.
	 * 
	 * Student cannot enroll more sections if number of units exceed the maximum load.
	 * 
	 * @throws EnrollmentException
	 */
	@Test  (expected = EnrollmentException.class)
	public void cannotExceedMaximumLoad() throws EnrollmentException{
		Enrollment year2sem1 = new Enrollment(student, 2, StudentType.UNDERGRAD, Scholarship.NONE, "year2sem1");

		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		Section section1Jazzdance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1Jazzdance101");

		Schedule sched_MON_THU_1000_1130 = new Schedule(Day.MON_THU, TimeSlot.TS_1000_1130);
		Subject swimming1 = new Subject(new HashSet<Subject>(), "swimming1");
		Section section1Swimming1 = new Section(teacher, swimming1, sched_MON_THU_1000_1130, 2010, Semester.FIRST, "section1Swimming1");

		Schedule sched_MON_THU_1130_1300 = new Schedule(Day.MON_THU, TimeSlot.TS_1130_1300);
		Subject bowling102 = new Subject(new HashSet<Subject>(), "bowling102");
		Section section1Bowling102 = new Section(teacher, bowling102, sched_MON_THU_1130_1300, 2010, Semester.FIRST, "section1Bowling102");

		Schedule sched_MON_THU_1300_1430 = new Schedule(Day.MON_THU, TimeSlot.TS_1300_1430);
		Subject fencing101 = new Subject(new HashSet<Subject>(), "fencing101");
		Section section1Fencing101 = new Section(teacher, fencing101, sched_MON_THU_1300_1430, 2010, Semester.FIRST, "section1Fencing101");

		Schedule sched_MON_THU_1600_1730 = new Schedule(Day.MON_THU, TimeSlot.TS_1600_1730);
		Subject ballroom101 = new Subject(new HashSet<Subject>(), "Ballroom101");
		Section section1Ballroom101 = new Section(teacher, ballroom101, sched_MON_THU_1600_1730, 2010, Semester.FIRST, "section1Ballroom101");

		Schedule sched_TUE_FRI_0830_1000 = new Schedule(Day.TUE_FRI, TimeSlot.TS_0830_1000);
		Subject humanities1 = new Subject(new HashSet<Subject>(), "humanities1");
		Section section1Humanities1 = new Section(teacher, humanities1, sched_TUE_FRI_0830_1000, 2010, Semester.FIRST, "section1Humanities1");

		Schedule sched_TUE_FRI_1000_1130 = new Schedule(Day.TUE_FRI, TimeSlot.TS_1000_1130);
		Subject math101 = new Subject(new HashSet<Subject>(), "math101");
		Section section1Math101 = new Section(teacher, math101, sched_TUE_FRI_1000_1130, 2010, Semester.FIRST, "section1Math101");

		Schedule sched_WED_SAT_1000_1130 = new Schedule(Day.WED_SAT, TimeSlot.TS_1000_1130);
		Subject socialDance101 = new Subject(new HashSet<Subject>(), "SocialDance101");
		Section section1Socialdance101 =  new Section(teacher, socialDance101, sched_WED_SAT_1000_1130, 2010, Semester.FIRST, "section1Socialdance101");

		year2sem1.addSection(section1Jazzdance101);
		year2sem1.addSection(section1Swimming1);
		year2sem1.addSection(section1Bowling102);
		year2sem1.addSection(section1Fencing101);
		year2sem1.addSection(section1Ballroom101);
		year2sem1.addSection(section1Humanities1);
		year2sem1.addSection(section1Math101);
		year2sem1.addSection(section1Folkdance101);
		year2sem1.addSection(section1Socialdance101);
	}

	/**
	 * System calculates tuition fee. Undergrad - None scholar - 7 subjects
	 * 
	 * @throws EnrollmentException
	 */
	@Test
	public void calculateTuitionFee() throws EnrollmentException{
		Enrollment year2sem1 = new Enrollment(student, 2, StudentType.UNDERGRAD, Scholarship.NONE, "year2sem1");

		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		Section section1Jazzdance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1Jazzdance101");

		Schedule sched_MON_THU_1000_1130 = new Schedule(Day.MON_THU, TimeSlot.TS_1000_1130);
		Subject swimming1 = new Subject(new HashSet<Subject>(), "swimming1");
		Section section1Swimming1 = new Section(teacher, swimming1, sched_MON_THU_1000_1130, 2010, Semester.FIRST, "section1Swimming1");

		Schedule sched_MON_THU_1130_1300 = new Schedule(Day.MON_THU, TimeSlot.TS_1130_1300);
		Subject bowling102 = new Subject(new HashSet<Subject>(), "bowling102");
		Section section1Bowling102 = new Section(teacher, bowling102, sched_MON_THU_1130_1300, 2010, Semester.FIRST, "section1Bowling102");

		Schedule sched_MON_THU_1300_1430 = new Schedule(Day.MON_THU, TimeSlot.TS_1300_1430);
		Subject fencing101 = new Subject(new HashSet<Subject>(), "fencing101");
		Section section1Fencing101 = new Section(teacher, fencing101, sched_MON_THU_1300_1430, 2010, Semester.FIRST, "section1Fencing101");

		Schedule sched_MON_THU_1600_1730 = new Schedule(Day.MON_THU, TimeSlot.TS_1600_1730);
		Subject ballroom101 = new Subject(new HashSet<Subject>(), "Ballroom101");
		Section section1Ballroom101 = new Section(teacher, ballroom101, sched_MON_THU_1600_1730, 2010, Semester.FIRST, "section1Ballroom101");

		Schedule sched_TUE_FRI_0830_1000 = new Schedule(Day.TUE_FRI, TimeSlot.TS_0830_1000);
		Subject humanities1 = new Subject(new HashSet<Subject>(), "humanities1");
		Section section1Humanities1 = new Section(teacher, humanities1, sched_TUE_FRI_0830_1000, 2010, Semester.FIRST, "section1Humanities1");

		Schedule sched_TUE_FRI_1000_1130 = new Schedule(Day.TUE_FRI, TimeSlot.TS_1000_1130);
		Subject math101 = new Subject(new HashSet<Subject>(), "math101");
		Section section1Math101 = new Section(teacher, math101, sched_TUE_FRI_1000_1130, 2010, Semester.FIRST, "section1Math101");

		year2sem1.addSection(section1Jazzdance101);
		year2sem1.addSection(section1Swimming1);
		year2sem1.addSection(section1Bowling102);
		year2sem1.addSection(section1Fencing101);
		year2sem1.addSection(section1Ballroom101);
		year2sem1.addSection(section1Humanities1);
		year2sem1.addSection(section1Math101);

		assertEquals(new BigDecimal("16000"), year2sem1.computeTuitionFee());
	}

	/**
	 * System calculates tuition fee. Grad - Half scholar - 7 subjects
	 * 
	 * @throws EnrollmentException
	 */
	@Test
	public void calculateGradTuitionFeeWithHalfDiscount() throws EnrollmentException{
		Student student = new Student("2009-12345");
		Enrollment year2sem1 = new Enrollment(student, 2, StudentType.GRAD, Scholarship.HALF, "year2sem1");

		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		Section section1Jazzdance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1Jazzdance101");

		Schedule sched_MON_THU_1000_1130 = new Schedule(Day.MON_THU, TimeSlot.TS_1000_1130);
		Subject swimming1 = new Subject(new HashSet<Subject>(), "swimming1");
		Section section1Swimming1 = new Section(teacher, swimming1, sched_MON_THU_1000_1130, 2010, Semester.FIRST, "section1Swimming1");

		Schedule sched_MON_THU_1130_1300 = new Schedule(Day.MON_THU, TimeSlot.TS_1130_1300);
		Subject bowling102 = new Subject(new HashSet<Subject>(), "bowling102");
		Section section1Bowling102 = new Section(teacher, bowling102, sched_MON_THU_1130_1300, 2010, Semester.FIRST, "section1Bowling102");

		Schedule sched_MON_THU_1300_1430 = new Schedule(Day.MON_THU, TimeSlot.TS_1300_1430);
		Subject fencing101 = new Subject(new HashSet<Subject>(), "fencing101");
		Section section1Fencing101 = new Section(teacher, fencing101, sched_MON_THU_1300_1430, 2010, Semester.FIRST, "section1Fencing101");

		Schedule sched_MON_THU_1600_1730 = new Schedule(Day.MON_THU, TimeSlot.TS_1600_1730);
		Subject ballroom101 = new Subject(new HashSet<Subject>(), "Ballroom101");
		Section section1Ballroom101 = new Section(teacher, ballroom101, sched_MON_THU_1600_1730, 2010, Semester.FIRST, "section1Ballroom101");

		Schedule sched_TUE_FRI_0830_1000 = new Schedule(Day.TUE_FRI, TimeSlot.TS_0830_1000);
		Subject humanities1 = new Subject(new HashSet<Subject>(), "humanities1");
		Section section1Humanities1 = new Section(teacher, humanities1, sched_TUE_FRI_0830_1000, 2010, Semester.FIRST, "section1Humanities1");

		Schedule sched_TUE_FRI_1000_1130 = new Schedule(Day.TUE_FRI, TimeSlot.TS_1000_1130);
		Subject math101 = new Subject(new HashSet<Subject>(), "math101");
		Section section1Math101 = new Section(teacher, math101, sched_TUE_FRI_1000_1130, 2010, Semester.FIRST, "section1Math101");

		year2sem1.addSection(section1Jazzdance101);
		year2sem1.addSection(section1Swimming1);
		year2sem1.addSection(section1Bowling102);
		year2sem1.addSection(section1Fencing101);
		year2sem1.addSection(section1Ballroom101);
		year2sem1.addSection(section1Humanities1);
		year2sem1.addSection(section1Math101);

		assertEquals(new BigDecimal("16000"), year2sem1.computeTuitionFee());
	}

	/**
	 * System calculates tuition fee. Undergrad - Half scholar - 7 subject
	 * 
	 * @throws EnrollmentException
	 */
	@Test
	public void calculateUndergradTuitionFeeWithHalfDiscount() throws EnrollmentException{
		Student student = new Student("2009-12345");
		Enrollment year2sem1 = new Enrollment(student, 2, StudentType.UNDERGRAD, Scholarship.HALF, "year2sem1");

		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		Section section1Jazzdance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1Jazzdance101");

		Schedule sched_MON_THU_1000_1130 = new Schedule(Day.MON_THU, TimeSlot.TS_1000_1130);
		Subject swimming1 = new Subject(new HashSet<Subject>(), "swimming1");
		Section section1Swimming1 = new Section(teacher, swimming1, sched_MON_THU_1000_1130, 2010, Semester.FIRST, "section1Swimming1");

		Schedule sched_MON_THU_1130_1300 = new Schedule(Day.MON_THU, TimeSlot.TS_1130_1300);
		Subject bowling102 = new Subject(new HashSet<Subject>(), "bowling102");
		Section section1Bowling102 = new Section(teacher, bowling102, sched_MON_THU_1130_1300, 2010, Semester.FIRST, "section1Bowling102");

		Schedule sched_MON_THU_1300_1430 = new Schedule(Day.MON_THU, TimeSlot.TS_1300_1430);
		Subject fencing101 = new Subject(new HashSet<Subject>(), "fencing101");
		Section section1Fencing101 = new Section(teacher, fencing101, sched_MON_THU_1300_1430, 2010, Semester.FIRST, "section1Fencing101");

		Schedule sched_MON_THU_1600_1730 = new Schedule(Day.MON_THU, TimeSlot.TS_1600_1730);
		Subject ballroom101 = new Subject(new HashSet<Subject>(), "Ballroom101");
		Section section1Ballroom101 = new Section(teacher, ballroom101, sched_MON_THU_1600_1730, 2010, Semester.FIRST, "section1Ballroom101");

		Schedule sched_TUE_FRI_0830_1000 = new Schedule(Day.TUE_FRI, TimeSlot.TS_0830_1000);
		Subject humanities1 = new Subject(new HashSet<Subject>(), "humanities1");
		Section section1Humanities1 = new Section(teacher, humanities1, sched_TUE_FRI_0830_1000, 2010, Semester.FIRST, "section1Humanities1");

		Schedule sched_TUE_FRI_1000_1130 = new Schedule(Day.TUE_FRI, TimeSlot.TS_1000_1130);
		Subject math101 = new Subject(new HashSet<Subject>(), "math101");
		Section section1Math101 = new Section(teacher, math101, sched_TUE_FRI_1000_1130, 2010, Semester.FIRST, "section1Math101");

		year2sem1.addSection(section1Jazzdance101);
		year2sem1.addSection(section1Swimming1);
		year2sem1.addSection(section1Bowling102);
		year2sem1.addSection(section1Fencing101);
		year2sem1.addSection(section1Ballroom101);
		year2sem1.addSection(section1Humanities1);
		year2sem1.addSection(section1Math101);

		assertEquals(new BigDecimal("9000"), year2sem1.computeTuitionFee());
	}

	/**
	 * System calculates tuition fee. Grad - Full scholar - 7 subjects
	 * 
	 * @throws EnrollmentException
	 */
	@Test
	public void calculateTuitionFeeWithFullDiscount() throws EnrollmentException{
		Student student = new Student("2009-12345");
		Enrollment year2sem1 = new Enrollment(student, 2, StudentType.GRAD, Scholarship.FULL, "year2sem1");

		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		Section section1Jazzdance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1Jazzdance101");

		Schedule sched_MON_THU_1000_1130 = new Schedule(Day.MON_THU, TimeSlot.TS_1000_1130);
		Subject swimming1 = new Subject(new HashSet<Subject>(), "swimming1");
		Section section1Swimming1 = new Section(teacher, swimming1, sched_MON_THU_1000_1130, 2010, Semester.FIRST, "section1Swimming1");

		Schedule sched_MON_THU_1130_1300 = new Schedule(Day.MON_THU, TimeSlot.TS_1130_1300);
		Subject bowling102 = new Subject(new HashSet<Subject>(), "bowling102");
		Section section1Bowling102 = new Section(teacher, bowling102, sched_MON_THU_1130_1300, 2010, Semester.FIRST, "section1Bowling102");

		Schedule sched_MON_THU_1300_1430 = new Schedule(Day.MON_THU, TimeSlot.TS_1300_1430);
		Subject fencing101 = new Subject(new HashSet<Subject>(), "fencing101");
		Section section1Fencing101 = new Section(teacher, fencing101, sched_MON_THU_1300_1430, 2010, Semester.FIRST, "section1Fencing101");

		Schedule sched_MON_THU_1600_1730 = new Schedule(Day.MON_THU, TimeSlot.TS_1600_1730);
		Subject ballroom101 = new Subject(new HashSet<Subject>(), "Ballroom101");
		Section section1Ballroom101 = new Section(teacher, ballroom101, sched_MON_THU_1600_1730, 2010, Semester.FIRST, "section1Ballroom101");

		Schedule sched_TUE_FRI_0830_1000 = new Schedule(Day.TUE_FRI, TimeSlot.TS_0830_1000);
		Subject humanities1 = new Subject(new HashSet<Subject>(), "humanities1");
		Section section1Humanities1 = new Section(teacher, humanities1, sched_TUE_FRI_0830_1000, 2010, Semester.FIRST, "section1Humanities1");

		Schedule sched_TUE_FRI_1000_1130 = new Schedule(Day.TUE_FRI, TimeSlot.TS_1000_1130);
		Subject math101 = new Subject(new HashSet<Subject>(), "math101");
		Section section1Math101 = new Section(teacher, math101, sched_TUE_FRI_1000_1130, 2010, Semester.FIRST, "section1Math101");

		year2sem1.addSection(section1Jazzdance101);
		year2sem1.addSection(section1Swimming1);
		year2sem1.addSection(section1Bowling102);
		year2sem1.addSection(section1Fencing101);
		year2sem1.addSection(section1Ballroom101);
		year2sem1.addSection(section1Humanities1);
		year2sem1.addSection(section1Math101);

		assertEquals(new BigDecimal("2000"), year2sem1.computeTuitionFee());
	}
	
	/**
	 * System checks if student has enrolled in minimum load for this semester.
	 * @throws EnrollmentException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void hasMinimumLoad() throws IllegalArgumentException, EnrollmentException {
		Enrollment year2sem1 = new Enrollment(student, 2, StudentType.GRAD, Scholarship.FULL, "year2sem1");

		Schedule sched_MON_THU_0830_1000 = new Schedule(Day.MON_THU, TimeSlot.TS_0830_1000);
		Subject jazzDance101 = new Subject(new HashSet<Subject>(), "JazzDance101");
		Section section1Jazzdance101 = new Section(teacher, jazzDance101, sched_MON_THU_0830_1000, 2010, Semester.FIRST, "section1Jazzdance101");

		Schedule sched_MON_THU_1000_1130 = new Schedule(Day.MON_THU, TimeSlot.TS_1000_1130);
		Subject swimming1 = new Subject(new HashSet<Subject>(), "swimming1");
		Section section1Swimming1 = new Section(teacher, swimming1, sched_MON_THU_1000_1130, 2010, Semester.FIRST, "section1Swimming1");

		Schedule sched_MON_THU_1130_1300 = new Schedule(Day.MON_THU, TimeSlot.TS_1130_1300);
		Subject bowling102 = new Subject(new HashSet<Subject>(), "bowling102");
		Section section1Bowling102 = new Section(teacher, bowling102, sched_MON_THU_1130_1300, 2010, Semester.FIRST, "section1Bowling102");

		Schedule sched_MON_THU_1300_1430 = new Schedule(Day.MON_THU, TimeSlot.TS_1300_1430);
		Subject fencing101 = new Subject(new HashSet<Subject>(), "fencing101");
		Section section1Fencing101 = new Section(teacher, fencing101, sched_MON_THU_1300_1430, 2010, Semester.FIRST, "section1Fencing101");

		Schedule sched_MON_THU_1600_1730 = new Schedule(Day.MON_THU, TimeSlot.TS_1600_1730);
		Subject ballroom101 = new Subject(new HashSet<Subject>(), "Ballroom101");
		Section section1Ballroom101 = new Section(teacher, ballroom101, sched_MON_THU_1600_1730, 2010, Semester.FIRST, "section1Ballroom101");

		Schedule sched_TUE_FRI_0830_1000 = new Schedule(Day.TUE_FRI, TimeSlot.TS_0830_1000);
		Subject humanities1 = new Subject(new HashSet<Subject>(), "humanities1");
		Section section1Humanities1 = new Section(teacher, humanities1, sched_TUE_FRI_0830_1000, 2010, Semester.FIRST, "section1Humanities1");

		Schedule sched_TUE_FRI_1000_1130 = new Schedule(Day.TUE_FRI, TimeSlot.TS_1000_1130);
		Subject math101 = new Subject(new HashSet<Subject>(), "math101");
		Section section1Math101 = new Section(teacher, math101, sched_TUE_FRI_1000_1130, 2010, Semester.FIRST, "section1Math101");

		year2sem1.addSection(section1Jazzdance101);
		year2sem1.addSection(section1Swimming1);
		year2sem1.addSection(section1Bowling102);
		year2sem1.addSection(section1Fencing101);
		year2sem1.addSection(section1Ballroom101);
		year2sem1.addSection(section1Humanities1);
		year2sem1.addSection(section1Math101);
		
		assertTrue(year2sem1.computeCurrentLoad() >= year2sem1.computeMinLoad());
	}

}
