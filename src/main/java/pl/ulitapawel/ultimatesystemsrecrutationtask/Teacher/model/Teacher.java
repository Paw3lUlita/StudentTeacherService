package pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.model.Student;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotEmpty(message = "Name cannot be empty!")
    @Size(min = 2, message = "Name has to have at least 2 characters")
    private String name;

    @NotEmpty(message = "Surname cannot be empty!")
    @Size(min = 2, message = "Surname has to have at least 2 characters")
    private String surname;

    @NotEmpty(message = "Age cannot be empty!")
    @Min(value = 2, message = "Age must be greater than 18")
    private int age;

    @NotEmpty(message = "Email cannot be empty!")
    @Email
    private String email;

    @NotEmpty(message = "Subject cannot be empty!")
    private String subject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;


}
