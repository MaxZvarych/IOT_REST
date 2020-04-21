package ua.lviv.iot.spring.first.rest.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.spring.first.business.StudentService;
import ua.lviv.iot.spring.first.rest.model.Student;

@RequestMapping("/students")
@RestController
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents(
            final @RequestParam(name = "firstName", required = false) String firstName) {
        if (firstName == null) {
            return studentService.findAll();
        }
        return studentService.getAllByFirstName(firstName);
    }

    @GetMapping(path = "/{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return studentService.find(id);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public Student createStudent(final @RequestBody Student student) {

        return studentService.createStudent(student);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer studentId) {
        if (studentService.find(studentId) != null) {
            studentService.deleteStudent(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer studentId,
            @RequestBody Student student) {
        if (studentService.find(studentId) != null) {
            student.setId(studentId);
            return new ResponseEntity<>(studentService.updateStudent(studentId, student),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
