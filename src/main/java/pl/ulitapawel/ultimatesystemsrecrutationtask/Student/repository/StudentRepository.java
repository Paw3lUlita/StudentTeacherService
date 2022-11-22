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

    @Query("SELECT t FROM Teacher t JOIN Student s WHERE s.id = :studentId")
    List<Teacher> findAllTeachersForStudent(long studentId);

}
