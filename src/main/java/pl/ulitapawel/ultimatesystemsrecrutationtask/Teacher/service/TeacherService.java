package pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    public List<Teacher> findAllByName(String name){
        return teacherRepository.findAllByNameContains(name);
    }

    @Transactional
    public List<Teacher> findAllBySurname(String surname){
        return teacherRepository.findAllBySurnameContains(surname);
    }

    @Transactional
    public List<Teacher> findAllByNameAndSurname(String name, String surname){
        return teacherRepository.findAllByNameContainsAndSurnameContains(name, surname);
    }

    @Transactional
    public Teacher findById(long teacherId){
        return teacherRepository.findById(teacherId).orElseThrow();
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
    public void updateTeacher(long teacherId, UpdateTeacherCommand teacherToUpdate){

        Teacher actualTeacher = teacherRepository.findById(teacherId).orElseThrow();

        if(teacherToUpdate.getName() != null) {
            actualTeacher.setName(teacherToUpdate.getName());
        }
        if(teacherToUpdate.getSurname() != null) {
            actualTeacher.setSurname(teacherToUpdate.getSurname());
        }
        if(teacherToUpdate.getAge() >= 18) {
            actualTeacher.setAge(teacherToUpdate.getAge());
        }
        if(teacherToUpdate.getEmail() != null) {
            actualTeacher.setEmail(teacherToUpdate.getEmail());
        }


        teacherRepository.save(actualTeacher);
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UpdateTeacherCommand {
        private String name;
        private String surname;
        private int age;
        private String email;
        private String subject;
    }

    @Transactional
    public void deleteTeacher(long id){
        teacherRepository.deleteById(id);
    }



}


