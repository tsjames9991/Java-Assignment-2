package org.knoldus.operations;

import org.knoldus.model.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Operation {
    public List<Student> studentWithNoSubject(List<ClassRoom> classRoomList) {
        return classRoomList.stream()
                .filter(classRoom -> classRoom.getStudentList().isPresent()).flatMap(classRoomWithStudent -> classRoomWithStudent.getStudentList().get().stream()
                        .filter(student -> !student.getSubjectList().isPresent())).collect(Collectors.toList());
    }

    public List<String> sayHelloStudent(List<ClassRoom> classRoomList) {

        return classRoomList.stream()
                .filter(classRoom -> classRoom.getStudentList().isPresent())
                .flatMap(classWithStudent -> classWithStudent.getStudentList().get()
                        .stream().map(student -> "Hello" + student.getName())).collect(Collectors.toList());
    }

    public List<Optional<List<String>>> getSubject(Integer id, List<ClassRoom> classRoomList) {
        return classRoomList.stream()
                .filter(classRoom -> classRoom.getStudentList().isPresent() && classRoom.getRoomId() == id)
                .flatMap(classWithStudent -> classWithStudent.getStudentList().get()
                        .stream().map(student -> student.getSubjectList())).collect(Collectors.toList());
    }

}
