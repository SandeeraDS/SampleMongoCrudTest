package com.ds.mongoTest;

import com.ds.mongoTest.bean.Address;
import com.ds.mongoTest.bean.Student;
import com.ds.mongoTest.enums.Gender;
import com.ds.mongoTest.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class MongoTestApplication {
    @Value("${initial.useMongoTemplate}")
    private int useMongoTemplate;
    private static final Logger logger = LogManager.getLogger(MongoTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MongoTestApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address("Sri Lanka", "Galle", "23586");
            Student student = new Student(
                    "Sandeera",
                    "Jayampathi",
                    "sandeerads@gmail.com",
                    Gender.MALE,
                    address,
                    List.of("History", "Geography"),
                    10.0,
                    new java.util.Date());

            if (useMongoTemplate == 1) {
                usingMongoTemplateAndQuery(repository, mongoTemplate, student);
            } else {
                usingRepositoryMethod(repository, student);
            }
        };
    }

    private static void usingRepositoryMethod(StudentRepository repository, Student student) {
        repository.findStudentByEmail(student.getEmail()).ifPresentOrElse(
                student2 -> logger.info("Student already available for email {}", student.getEmail())
                , () -> {
                    logger.info("Inserting student {}", student);
                    repository.insert(student);
                }
        );
    }

    private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(student.getEmail()));
        List<Student> students = mongoTemplate.find(query, Student.class);
        if (students.isEmpty()) {
            repository.insert(student);
        } else {
            logger.info("Student already available for email {}", student.getEmail());
        }
    }

}
