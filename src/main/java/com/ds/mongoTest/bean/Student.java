package com.ds.mongoTest.bean;

import com.ds.mongoTest.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private Double totalSpentInBooks;
    private java.util.Date createdDate;

    public Student(String firstName, String lastName, String email, Gender gender, Address address, List<String> favouriteSubjects, Double totalSpentInBooks, java.util.Date createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.createdDate = createdDate;
    }
}
