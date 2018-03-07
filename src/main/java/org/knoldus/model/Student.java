package org.knoldus.model;

import java.util.List;
import java.util.Optional;

public class Student {

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public Optional<List<String>> getSubjectList() {
        return subjectList;
    }

    private String name;
    private int rollNumber;
    private Optional<List<String>> subjectList;

    public Student(String name, Integer rollNo, Optional<List<String>> subjectList) {
        this.name = name;
        this.rollNumber = rollNo;
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return name + " " + rollNumber + " " + subjectList;
    }
}