package com.company;

/**
 * Created by yuexiaoli on 2017/10/25.
 */
public class Student {
    private String studentID;
    private String name;
    private int age;
    private int score1;
    private int score2;

    Student(String studentID,String name,int age,int score1,int score2) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.score1 = score1;
        this.score2 = score2;
    }

    public String getStudentInfo(){
        String studentInfo = studentID + " " + name + " " + age + " " + score1 + " " +score2 + " ";
        return studentInfo;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}
