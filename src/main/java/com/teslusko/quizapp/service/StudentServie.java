package com.teslusko.quizapp.service;

import com.teslusko.quizapp.dao.StudentDao;
import com.teslusko.quizapp.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServie {
    @Autowired
    StudentDao studentDao;
    public ResponseEntity<Student> addStudent(Student student) {
        Student s = new Student();
        s.setStudentEmail(student.getStudentEmail());
        s.setStudentName(student.getStudentName());
        s.setStudentSurname(student.getStudentSurname());
        return new ResponseEntity<Student>(studentDao.save(student),HttpStatus.CREATED);

    }

    public ResponseEntity<List<Student>> getAllStudent() {
        try {
            return new ResponseEntity<>(studentDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Student> getStudentById(String id) {
        try {
            return new ResponseEntity<>(studentDao.findById(id).get(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Student>(new Student(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Student> updateStudent(Student student) {
        List<Student> students = studentDao.findAll();
        for (Student s : students) {
            if(s.getStudentId().equals(student.getStudentId())) {
                s.setStudentEmail(student.getStudentEmail());
                s.setStudentName(student.getStudentName());
                s.setStudentSurname(student.getStudentSurname());
                studentDao.save(s);
                return new ResponseEntity<Student>(getStudentById(student.getStudentId()).getBody(),HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<String> deleteStudent(String id) {
        List<Student> students = studentDao.findAll();
        for (Student s : students) {
            if(s.getStudentId().equals(id)) {
                studentDao.delete(s);
                return new  ResponseEntity<String>("Student deleted", HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }
}
