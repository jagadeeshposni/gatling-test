package com.posni.gatling.gatlingtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class Student {
    int id;
    String name;
    String grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{"+
                "id="+id+
                ", name='"+name+'\''+
                ", grade='"+grade+'\''+
                '}';
    }
}
