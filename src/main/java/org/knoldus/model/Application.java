package org.knoldus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Application is the entry point of the demonstration.
 */
final class Application {

    /**
     * Constant Values For Initialization
     */
    private static final int ONE = 1;
    /**
     * Constant Values For Initialization
     */
    private static final int TWO = 2;
    /**
     * Constant Values For Initialization
     */
    private static final int THREE = 3;
    /**
     * Constant Values For Initialization
     */
    private static final int FOUR = 4;

    /**
     * private constructor so that this class wouldn't get instantiated later in any other package.
     */
    private Application() {

    }

    /**
     * Entry point.
     * @param args : console time argument are specified here.
     */
    public static void main(final String[] args) {

        List<Student> students = new ArrayList<>();
        List<String> subjectsBackEnd = new ArrayList<>();
        subjectsBackEnd.add("akka");
        subjectsBackEnd.add("scala");
        List<String> subjectsFrontEnd = new ArrayList<>();
        subjectsFrontEnd.add("html");
        subjectsFrontEnd.add("angular 2");
        students.add(new Student("Sudeep", ONE, Optional.empty()));
        students.add(new Student("Abhishek", TWO, Optional.of(subjectsBackEnd)));
        students.add(new Student("Shahab", THREE, Optional.of(subjectsFrontEnd)));
        students.add(new Student("Vinay", FOUR, Optional.of(subjectsBackEnd)));
        ClassRoom roomOne = new ClassRoom(ONE, Optional.of(students));
        ClassRoom storeRoom = new ClassRoom(TWO, Optional.empty());
        ClassRoom roomTwo = new ClassRoom(THREE,
                Optional.of(students.subList(ONE, THREE)));
        List<ClassRoom> rooms = new ArrayList<>();
        rooms.add(roomOne);
        rooms.add(storeRoom);
        rooms.add(roomTwo);
        System.out.println("Student with no subjects associated");
        roomOne.getStudentsWithNoSubjects()
                .forEach(System.out::println);
        System.out.println("\n");
        System.out.println("Subjects studied by students in room number 3");
        rooms.stream()
                .filter(room -> room.getRoomId() == THREE)
                .map(ClassRoom::getStudentsSubject)
                .forEach(subject -> subject.forEach(System.out::println));
        System.out.println("\n");
        System.out.println("In room where students are present , greet them");
        rooms.forEach(room -> System.out.println(room.greetStudents()));
    }
}