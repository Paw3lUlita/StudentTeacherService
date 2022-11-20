package pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.service;

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
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    public Teacher findByName(String name){
        return teacherRepository.findByNameContains(name);
    }

    @Transactional Teacher findBySurname(String surname){
        return teacherRepository.findBySurnameContains(surname);
    }

    @Transactional Teacher findByNameAndSurname(String name, String surname){
        return teacherRepository.findByNameContainsAndSurnameContains(name, surname);
    }

    @Transactional
    public List<Student> findAllStudentsForTeacher(long teacherId){
        return teacherRepository.findAllStudentsForTeacher(teacherId);
    }

    @Transactional
    public void addStudentForTeacher(long teacherId, long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow();
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();

        teacher.getStudents().add(student);

        teacherRepository.save(teacher);

    }

    @Transactional
    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    @Transactional
    public void updateTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    @Transactional
    public void deleteTeacher(long id){
        teacherRepository.deleteById(id);
    }

}
