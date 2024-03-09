package com.ds.mongoTest.util;

import com.ds.mongoTest.bean.Address;
import com.ds.mongoTest.bean.Student;
import com.ds.mongoTest.enums.Gender;
import com.ds.mongoTest.model.StudentModel;

import java.util.Date;
import java.util.List;

public class StudentPopulator {

    private StudentPopulator(){
        // Keep empty
    }

    public static Student populateStudent(StudentModel studentModel) {
        Student student = new Student();
        student.setFirstName(studentModel.getFirstName());
        student.setLastName(studentModel.getLastName());
        student.setEmail(studentModel.getEmail());
        student.setTotalSpentInBooks(studentModel.getTotalSpentInBooks());
        student.setCreatedDate(new Date());
        student.setAddress(new Address(studentModel.getCountry(), studentModel.getCity(), studentModel.getPostCode()));
        if (studentModel.getGender() == 0) {
            student.setGender(Gender.MALE);
        } else {
            student.setGender(Gender.FEMALE);
        }
        if (studentModel.getFavouriteSubjects() != null && !studentModel.getFavouriteSubjects().isBlank()) {
            List<String> subjects = List.of(studentModel.getFavouriteSubjects().split(","));
            student.setFavouriteSubjects(subjects);
        }
        return student;
    }
}
