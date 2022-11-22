package pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Page<Teacher> findAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Transactional
    public Page<Teacher> findAllByName(String name, Pageable pageable){
        return teacherRepository.findAllByNameContains(name,  pageable);
    }

    @Transactional
    public Page<Teacher> findAllBySurname(String surname, Pageable pageable){
        return teacherRepository.findAllBySurnameContains(surname, pageable);
    }

    @Transactional
    public Page<Teacher> findAllByNameAndSurname(String name, String surname, Pageable pageable){
        return teacherRepository.findAllByNameContainsAndSurnameContains(name, surname, pageable);
    }

    @Transactional
    public Teacher findById(long teacherId){
        return teacherRepository.findById(teacherId).orElseThrow();
    }

    @Transactional
    public Page<Student> findAllStudentsForTeacher(long teacherId, Pageable pageable){

        return studentRepository.findAllByTeachers(teacherId, pageable);
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
    public void updateTeacher(long teacherId, Teacher teacherToUpdate){

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



    @Transactional
    public void deleteTeacher(long id){
        teacherRepository.deleteById(id);
    }


}


