package com.shaikhabdulgani.crudrest.controller;

import com.shaikhabdulgani.crudrest.exception.NotFoundException;
import com.shaikhabdulgani.crudrest.model.Student;
import com.shaikhabdulgani.crudrest.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody @Valid Student student){
        return service.save(student);
    }

    @GetMapping("students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId) throws NotFoundException {
        return ResponseEntity.ok(service.getStudentById(studentId));
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "NONE",required = false) StudentService.SortBy sortBy,
            @RequestParam(value = "dir",defaultValue = "ASC",required = false) Sort.Direction dir
            ){
        return ResponseEntity.ok(service.getAll(pageNumber,pageSize,sortBy,dir));
    }

    @PutMapping("students/{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student,@PathVariable String studentId) throws NotFoundException {
        return ResponseEntity.ok(service.update(student,studentId));
    }

    @DeleteMapping("students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId) throws NotFoundException {
        return ResponseEntity.ok(service.deleteById(studentId));
    }

    @DeleteMapping("students")
    public ResponseEntity<String> deleteAllStudent(){
        return ResponseEntity.ok(service.deleteAll());
    }
}
