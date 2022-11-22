package pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.model.Student;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.model.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Page<Teacher> findAllByNameContains(String name, Pageable pageable);

    Page<Teacher> findAllBySurnameContains(String Surname, Pageable pageable);

    Page<Teacher> findAllByNameContainsAndSurnameContains(String name, String Surname, Pageable pageable);

    @Query(value = "SELECT t.id, t.age, t.email, t.name, t.subject, t.surname FROM teacher t JOIN teacher_student ts on t.id = ts.teacher_id WHERE student_id = :studentId", nativeQuery = true)
    Page<Teacher> findAllTeachersForStudent(long studentId, Pageable pageable);


}