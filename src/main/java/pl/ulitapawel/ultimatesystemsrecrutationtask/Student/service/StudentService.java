package pl.ulitapawel.ultimatesystemsrecrutationtask.Student.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.model.Student;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.repository.StudentRepository;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.model.Teacher;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.repository.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student findByName(String name) {
        return studentRepository.findByNameContains(name);
    }

    @Transactional
    public Student findBySurname(String surname) {
        return studentRepository.findBySurnameContains(surname);
    }

    @Transactional
    public Student findByNameAndSurname(String name, String surname) {
        return studentRepository.findByNameContainsAndSurnameContains(name, surname);
    }

    @Transactional
    public List<Teacher> findAllTeachersForStudent(long studentId) {
        return studentRepository.findAllTeachersForStudent(studentId);
    }

    @Transactional
    public void addTeacherForStudent(long studentId, long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();

        student.getTeachers().add(teacher);

        studentRepository.save(student);

    }

    @Transactional
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

}
