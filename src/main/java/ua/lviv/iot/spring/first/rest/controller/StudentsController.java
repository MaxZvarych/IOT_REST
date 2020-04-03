package ua.lviv.iot.spring.first.rest.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.iot.spring.first.business.StudentService;
import ua.lviv.iot.spring.first.rest.model.Student;

@RequestMapping("/students")
@RestController
public class StudentsController {

    private Map<Integer, Student> students = new HashMap<>();

    private AtomicInteger idCounter = new AtomicInteger();

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return new LinkedList<Student>(students.values());
    }

    @GetMapping(path = "/{id}")
    public Student getStudent(final @PathVariable("id") Integer studentId) {
        return students.get(studentId);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public Student createStudent(final @RequestBody Student student) {

        return studentService.createStudent(student);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer studentId) {
        HttpStatus status = students.remove(studentId) == null ? HttpStatus.NOT_FOUND
                : HttpStatus.OK;
        return ResponseEntity.status(status).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer studentId) {
        boolean exists = false;
        List<Student> list = getStudents();

        for (int i = 0; i < list.size(); i++) {
            if (studentId.equals(list.get(i).getId())) {
                exists = true;
            }
        }

        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}