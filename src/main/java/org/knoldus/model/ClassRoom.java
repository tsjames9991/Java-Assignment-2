package org.knoldus.model;

import java.util.List;
import java.util.Optional;

public class ClassRoom {

    public Integer getRoomId() {
        return roomId;
    }

    public Optional<List<Student>> getStudentList() {
        return studentList;
    }

    private Integer roomId;
    private Optional<List<Student>> studentList;

    public ClassRoom(Integer roomId, Optional<List<Student>> studentList) {
        this.roomId = roomId;
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return roomId + " " + studentList;
    }
}
