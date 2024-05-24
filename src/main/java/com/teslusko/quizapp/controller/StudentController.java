package com.teslusko.quizapp.controller;

import com.teslusko.quizapp.model.Student;
import com.teslusko.quizapp.service.StudentServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentServie studentServie;

    @PostMapping("add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student s = new Student();
        return studentServie.addStudent(student);

    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentServie.getAllStudent();
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return studentServie.getStudentById(id);
    }
    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return studentServie.updateStudent(student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        return studentServie.deleteStudent( id);
    }
}
