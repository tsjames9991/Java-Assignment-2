package org.knoldus.model;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test suite for testing ClassRoom class.
 */

public class ClassRoomTester {
    /**
     * Constant Values For Initialization.
     */
    private static final int ONE = 1;
    /**
     * Constant Values For Initialization.
     */
    private static final int TWO = 2;
    /**
     * Constant Values For Initialization.
     */
    private static final int THREE = 3;
    /**
     * Constant Values For Initialization.
     */
    private static final int FOUR = 4;
    /**
     * Constant Values For Initialization.
     */
    private static ClassRoom classRoom;
    /**
     * Constant Values For Initialization.
     */
    private static ClassRoom commonRoom;
    /**
     * Constant Values For Initialization.
     */
    private static List<Student> students;

    @BeforeClass
    public static void setUp() {
        List<String> subjectsBackEnd = new ArrayList<>();
        subjectsBackEnd.add("akka");
        subjectsBackEnd.add("scala");

        List<String> subjectsFrontEnd = new ArrayList<>();
        subjectsFrontEnd.add("html");
        subjectsFrontEnd.add("angular 2");
        students = new ArrayList<>();
        students.add(new Student("Sudeep", ONE, Optional.empty()));
        students.add(new Student("Abhishek", TWO, Optional.of(subjectsBackEnd)));
        students.add(new Student("Shahab", THREE, Optional.of(subjectsFrontEnd)));
        students.add(new Student("Vinay", FOUR, Optional.of(subjectsBackEnd)));
        classRoom = new ClassRoom(1, Optional.of(students));
        commonRoom = new ClassRoom(2, Optional.empty());
    }

    /**
     * tests Room number of a room.
     */
    @Test
    public final void testGetRoomId() {
        int actualResult = classRoom.getRoomId();
        int expectedResult = 1;
        assertEquals("Getting a room id must result in 1",
                expectedResult, actualResult);
    }

    /**
     * checks whether the room has students or not.
     */
    @Test
    public final void testGetStudents() {
        assertEquals("Room must have students",
                classRoom.hasStudents(), true);
    }

    /**
     * checks whether the store room has students or not.
     */
    @Test
    public final void testGetStudentsStore() {
        assertEquals("Store Room must not have students",
                commonRoom.hasStudents(), false);
    }

    /**
     * checks students with no subjects.
     */
    @Test
    public final void testGetStudentsWithNoSubject() {
        Stream<Student> expectedResult =
                students.stream().filter(student -> !student.hasSubjects());
        assertArrayEquals("Room 1 has Only one student with no subjects",
                classRoom.getStudentsWithNoSubjects().toArray(),
                expectedResult.toArray());
    }

    /**
     * checks students with no subjects in store room.
     */
    @Test
    public final void testGetStudentsWithNoSubjectForStore() {
        assertArrayEquals("Store Room has no students",
                commonRoom.getStudentsWithNoSubjects().toArray(),
                new Student[]{});
    }

    /**
     * checks if to see if the room greets students.
     */
    @Test
    public final void testGreetStudent() {
        String expectedResult = "Room: " + classRoom.getRoomId() + " Hello Students!";
        assertEquals("Room has students so they will be greeted",
                classRoom.greetStudents(), expectedResult);
    }

    /**
     * checks to see if store room greets the students.
     */
    @Test
    public final void testGreetStudentInStoreRoom() {
        String expectedResult = "Room: " + commonRoom.getRoomId() + "";
        assertEquals("Store room has no one,  so no greetings",
                commonRoom.greetStudents(), expectedResult);
    }

    /**
     * checks whether the subjects.
     */
    @Test
    public final void testGetStudentSubjects() {
        Stream<String> expectedSubjects = students.stream()
                .map(Student::getSubjects)
                .flatMap(Collection::stream);

        assertArrayEquals("Returns all the subjects",
                classRoom.getStudentsSubject().toArray(),
                expectedSubjects.toArray());
    }

}
