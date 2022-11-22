package pl.ulitapawel.ultimatesystemsrecrutationtask.Student.model;

import lombok.*;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.model.Teacher;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name cannot be empty!")
    @Size(min = 2, message = "Name has to have at least 2 characters")
    private String name;

    @NotEmpty(message = "Surname cannot be empty!")
    @Size(min = 2, message = "Surname has to have at least 2 characters")
    private String surname;


    @Min(value = 2, message = "Age must be greater than 18")
    private int age;

    @NotEmpty(message = "Email cannot be empty!")
    @Email
    private String email;

    @NotEmpty(message = "Faculty cannot be empty")
    private String faculty;

    /*@ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers;*/
}
