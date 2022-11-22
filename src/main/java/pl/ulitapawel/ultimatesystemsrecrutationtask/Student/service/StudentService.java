package pl.ulitapawel.ultimatesystemsrecrutationtask.Student.service;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Transactional
    public Page<Student> findAllByName(String name, Pageable pageable) {
        return studentRepository.findByNameContains(name, pageable);
    }

    @Transactional
    public Page<Student> findAllBySurname(String surname, Pageable pageable) {
        return studentRepository.findBySurnameContains(surname, pageable);
    }

    @Transactional
    public Page<Student> findAllByNameAndSurname(String name, String surname, Pageable pageable) {
        return studentRepository.findByNameContainsAndSurnameContains(name, surname, pageable);
    }

    @Transactional
    public Student findById(long studentId){
        return studentRepository.findById(studentId).orElseThrow();
    }

    @Transactional
    public Page<Teacher> findAllTeachersForStudent(long studentId, Pageable pageable) {
        return teacherRepository.findAllTeachersForStudent(studentId, pageable);
    }

    @Transactional
    public void addTeacherForStudent(long studentId, long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();

        teacher.getStudents().add(student);

        teacherRepository.save(teacher);

    }

    @Transactional
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(long studentId, Student studentToUpdate) {
        Student actualStudent = studentRepository.findById(studentId).orElseThrow();

        if(studentToUpdate.getName() != null) {
            actualStudent.setName(studentToUpdate.getName());
        }
        if(studentToUpdate.getSurname() != null) {
            actualStudent.setSurname(studentToUpdate.getSurname());
        }
        if(studentToUpdate.getAge() >= 18) {
            actualStudent.setAge(studentToUpdate.getAge());
        }
        if(studentToUpdate.getEmail() != null) {
            actualStudent.setEmail(studentToUpdate.getEmail());
        }
        studentRepository.save(actualStudent);

    }

    @Transactional
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

}
