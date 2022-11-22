package pl.ulitapawel.ultimatesystemsrecrutationtask.Student.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.model.Student;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.model.Teacher;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Page<Student> findByNameContains(String name, Pageable pageable);

    Page<Student> findBySurnameContains(String Surname, Pageable pageable);

    Page<Student> findByNameContainsAndSurnameContains(String name, String Surname, Pageable pageable);

    @Query(value = "SELECT s.id, s.age, s.email, s.name, s.faculty, s.surname FROM student s JOIN teacher_student ts on s.id = ts.student_id WHERE teacher_id = :teacherId", nativeQuery = true)
    Page<Student> findAllByTeachers(long teacherId, Pageable pageable);

}
