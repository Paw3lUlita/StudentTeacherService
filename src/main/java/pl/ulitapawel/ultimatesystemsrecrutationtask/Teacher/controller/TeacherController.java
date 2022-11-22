package pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.model.Student;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Student.service.StudentService;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.model.Teacher;
import pl.ulitapawel.ultimatesystemsrecrutationtask.Teacher.service.TeacherService;


import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final StudentService studentService;


    @GetMapping
    public Page<Teacher> getAll(Pageable pageable,
                                @RequestParam Optional<String> name,
                                @RequestParam Optional<String> surname) {
        if(name.isEmpty() && surname.isEmpty() ) {
            return teacherService.findAll(pageable);
        } else if (name.isPresent() && surname.isEmpty()){
            return teacherService.findAllByName(name.get(), pageable);
        } else if (name.isEmpty()){
            return teacherService.findAllBySurname(surname.get(), pageable);
        }else{
            return teacherService.findAllByNameAndSurname(name.get(), surname.get(), pageable);
        }
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable long id){
        return teacherService.findById(id);
    }

    @GetMapping("{id}/students")
    public Page<Student> findAllStudentsForTeacher(@PathVariable long id, Pageable pageable){
        return teacherService.findAllStudentsForTeacher(id, pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@RequestBody Teacher teacherToAdd){
        teacherService.saveTeacher(teacherToAdd);
    }

    @PutMapping("/{teacherId}/{studentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addStudentForTeacher(@PathVariable long teacherId, @PathVariable long studentId){
        teacherService.addStudentForTeacher(teacherId, studentId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTeacher(@PathVariable long id, @RequestBody Teacher teacherToUpdate) {
        teacherService.updateTeacher(id, teacherToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTeacher(@PathVariable long id){
        teacherService.deleteTeacher(id);
    }

    

}
