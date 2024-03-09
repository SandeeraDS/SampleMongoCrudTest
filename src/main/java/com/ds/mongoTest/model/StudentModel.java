package com.ds.mongoTest.model;
import lombok.Data;

@Data
public class StudentModel {
    private String firstName;
    private String lastName;
    private String email;
    private int gender;
    private String favouriteSubjects;
    private Double totalSpentInBooks;
    private String country;
    private String city;
    private String postCode;
}
