package org.knoldus.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * ClassRoom: Class representation of a class room.
 */
public class ClassRoom {

    /**
     * roomId : stands for the number of the room.
     */
    private int roomId;
    /**
     * studentList: stands for the students belonging to a room.
     */
    private Optional<List<Student>> studentList;

    /**
     Constructor to the class ClassRoom.
     */
    ClassRoom(final int roomNumber,
              final Optional<List<Student>> externalStudentList) {
        this.roomId = roomNumber;
        this.studentList = externalStudentList;
    }

    /**
     * returns the room number.
     * @return Something
     */
    public final int getRoomId() {
        return this.roomId;
    }

    /**
     * Returns true if students are present.
     * @return Something
     */
    public final boolean hasStudents() {
        return this.studentList.isPresent();
    }

    /**
     * getter for student list.
     * @return Something
     */
    private List<Student> getStudentList() {
        return this.studentList.orElseGet(ArrayList::new);
    }

    /**
     * Returns list of students with no subjects.
     * @return Something
     */
    public final Stream<Student> getStudentsWithNoSubjects() {
        if (this.studentList.isPresent()) {
            return this.studentList.get().stream()
                    .filter(student -> !student.hasSubjects());
        } else {
            return new ArrayList<Student>().stream();
        }

    }

    /**
     * Return a greeting message, if students are available.
     * @return Something
     */
    public final String greetStudents() {
        if (this.studentList.isPresent()) {
            return "Room: " + this.roomId + " Hello Students!";
        } else {
            return "Room: " + this.roomId + "";
        }
    }

    /**
     * Returns the subjects taught in this room.
     * @return Something
     */
    public final Stream<String> getStudentsSubject() {
        return (
                this.getStudentList().stream()
                        .map(Student::getSubjects)
                        .flatMap(Collection::stream)
        );

    }
}
