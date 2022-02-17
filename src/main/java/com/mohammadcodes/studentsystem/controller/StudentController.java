package com.mohammadcodes.studentsystem.controller;

import com.mohammadcodes.studentsystem.model.Student;
import com.mohammadcodes.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String add(@RequestBody Student student){
       studentService.saveStudent(student);
        return "New student is added";
    }

    @GetMapping("/getAll")
     public List<Student> getAllStudents(){
        return studentService.getAllStudents();

    }

    @GetMapping(value ="/{studentId}")
    public  Student getStudent(@PathVariable("studentId") Long studentId){
        return studentService.getStudent(studentId);
    }



    @DeleteMapping(path = "{studentId}")
    public  void deleteStudent(
            @PathVariable("studentId")Long studentId) {
        studentService.deleteStudent(studentId);
    }

//    @PutMapping("{studentId}")
//    public void updateStudent(
//            @PathVariable("studentId") Long studentId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String address){
//        studentService.updateStudent(studentId, name, address);
//
//    }

    @PutMapping(value = "/{studentId}")
    public void updateStudent( @PathVariable("studentId") Long studentId, @RequestBody Student student) {
        studentService.updateStudent(studentId, student);
    }
}
