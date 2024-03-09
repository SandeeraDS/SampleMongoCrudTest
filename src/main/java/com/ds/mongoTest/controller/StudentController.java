package com.ds.mongoTest.controller;

import com.ds.mongoTest.bean.Student;
import com.ds.mongoTest.model.StudentModel;
import com.ds.mongoTest.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private static final Logger logger = LogManager.getLogger(StudentController.class);
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("Get All Students");
        return studentService.getAllStudents();
    }

    @PostMapping
    public String addStudent(@RequestBody StudentModel studentModel) {
        return studentService.addStudent(studentModel);
    }

    @DeleteMapping
    public String deleteStudent(@Param("email") String email) {
        return studentService.deleteStudent(email);
    }

    @PutMapping("/updateSpentTime")
    public String deleteStudent(@Param("email") String email, @Param("spentTime") Double spentTime) {
        return studentService.updateSpentTimeOfStudent(email,spentTime);
    }

}
