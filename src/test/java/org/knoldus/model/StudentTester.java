package org.knoldus.model;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Test suite for class Student.
 */

public class StudentTester {
    /**
     * Constant Values For Initialization
     */
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static Student genius;
    private static Student misfit;
    private static List<String> subjectsBackEnd;

    @BeforeClass
    public static void setUp() {
        subjectsBackEnd = new ArrayList<>();
        subjectsBackEnd.add("akka");
        subjectsBackEnd.add("scala");
        misfit = new Student("James", TWO, Optional.empty());
        genius = new Student("Vinay", ONE, Optional.of(subjectsBackEnd));
    }

    /**
     * tests subjects of a student.
     */
    @Test
    public final void testGetSubjects() {
        assertArrayEquals("Student has 2 subjects",
                genius.getSubjects().toArray(),
                subjectsBackEnd.toArray());
    }

    /**
     * tests subjects of a student.
     */
    @Test
    public final void testGetSubjectsWithMisfit() {
        assertArrayEquals("Student has 0 subject assigned",
                misfit.getSubjects().toArray(),
                new String[]{});
    }

    /**
     * tests if name of student is present.
     */
    @Test
    public final void testGetName() {
        String expectedResult = "Vinay";
        assertEquals("Every student must have a name",
                genius.getName(),
                expectedResult);
    }

    /**
     * tests if a roll number is assigned to a student.
     */
    @Test
    public final void testGetRollNumber() {
        assertEquals("Student has roll number 1",
                genius.getRollNumber(),
                ONE);
    }

    /**
     * tests if subject(s) are present for a student.
     */
    @Test
    public final void testHasSubject() {
        assertEquals("Student has subjects", genius.hasSubjects(), true);
    }

    /**
     * tests if subject(s) are present for a student.
     */
    @Test
    public final void testHasNoSubject() {
        assertEquals("Student doesn't have any subject",
                misfit.hasSubjects(), false);
    }

}
