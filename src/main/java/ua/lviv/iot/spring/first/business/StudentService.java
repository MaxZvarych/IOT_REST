package ua.lviv.iot.spring.first.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.spring.first.dataacces.StudentRepository;
import ua.lviv.iot.spring.first.rest.model.Student;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public List<Student> getAllByFirstName(String firstName) {
		return studentRepository.findAllByFirstName(firstName);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Student find(Integer id) {
		return studentRepository.findById(id).get();
	}

	public void deleteStudent(Integer id) {
		if (studentRepository.findById(id).isPresent()) {
			studentRepository.deleteById(id);
		}
	}

	public Student updateStudent(Integer id, Student student) {
		if (studentRepository.findById(id).isPresent()) {
			return studentRepository.save(student);
		} else {
			return null;
		}
	}
}
