package pl.ulitapawel.ultimatesystemsrecrutationtask.Student.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.model.Student;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.service.StudentService;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.model.Teacher;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.service.TeacherService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("students")
public class StudentController {
    private final TeacherService teacherService;
    private final StudentService studentService;


    @GetMapping
    public Page<Student> getAll(Pageable pageable, @RequestParam Optional<String> name, @RequestParam Optional<String> surname) {
        if(name.isEmpty() && surname.isEmpty() ) {
            return studentService.findAll(pageable);
        } else if (name.isPresent() && surname.isEmpty()){
            return studentService.findAllByName(name.get(), pageable);
        } else if (name.isEmpty()){
            return studentService.findAllBySurname(surname.get(), pageable);
        }else{
            return studentService.findAllByNameAndSurname(name.get(), surname.get(), pageable);
        }
    }

    @GetMapping("/{id}")
    public Student getTeacherById(@PathVariable long id){
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student studentToAdd){
        studentService.saveStudent(studentToAdd);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateStudent(@PathVariable long id, @RequestBody Student studentToUpdate) {
        studentService.updateStudent(id, studentToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStudent(@PathVariable long id){
        studentService.deleteStudent(id);
    }



}
