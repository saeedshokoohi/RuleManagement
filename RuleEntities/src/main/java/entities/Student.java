package entities;

import java.util.List;

public class Student{
    private String name;
    private String lastName;
    private int grade;
    private List<Teacher> studentTeachers;

    public Student(String name, String lastName, int grade) {
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<Teacher> getStudentTeachers() {
        return studentTeachers;
    }

    public void setStudentTeachers(List<Teacher> studentTeachers) {
        this.studentTeachers = studentTeachers;
    }
    public String alert()
    {
        return this.getName();
    }
}

