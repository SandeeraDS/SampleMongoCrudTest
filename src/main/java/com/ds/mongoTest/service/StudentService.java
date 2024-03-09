package com.ds.mongoTest.service;

import com.ds.mongoTest.bean.Student;
import com.ds.mongoTest.model.StudentModel;
import com.ds.mongoTest.repository.StudentRepository;
import com.ds.mongoTest.util.StudentPopulator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger logger = LogManager.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String addStudent(StudentModel studentModel) {
        logger.info("add student {}", studentModel);
        try {
            Student student = StudentPopulator.populateStudent(studentModel);
            studentRepository.insert(student);
            return "1";
        } catch (Exception e) {
            logger.error("error occurred when adding student {}", e.getMessage(), e);
        }
        return "-1|Error in inserting new student";
    }

    public String deleteStudent(String studentEmail) {
        logger.info("delete student by email {}", studentEmail);
        try {
            Optional<Student> student = studentRepository.findStudentByEmail(studentEmail);
            if (student.isEmpty()) {
                return "-1|Student not found for " + studentEmail;
            }
            studentRepository.deleteById(student.get().getId());
            return "1";
        } catch (Exception e) {
            logger.error("error occurred when deleting {}", e.getMessage(), e);
        }
        return "-1|Error in deleting student " + studentEmail;
    }

    public String updateSpentTimeOfStudent(String studentEmail, Double spentTime) {
        logger.info("update spent time of {} student by email {}", spentTime, studentEmail);
        try {
            if(spentTime < 0){
                return "-1|Invalid spent time";
            }
            Optional<Student> student = studentRepository.findStudentByEmail(studentEmail);
            if (student.isEmpty()) {
                return "-1|Student not found for " + studentEmail;
            }
            student.get().setTotalSpentInBooks(spentTime);
            studentRepository.save(student.get());
            return "1";
        } catch (Exception e) {
            logger.error("error occurred when updating {}", e.getMessage(), e);
        }
        return "-1|Error in deleting student " + studentEmail;
    }
}
