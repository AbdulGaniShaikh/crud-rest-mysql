package com.shaikhabdulgani.crudrest.service;

import com.shaikhabdulgani.crudrest.exception.NotFoundException;
import com.shaikhabdulgani.crudrest.model.Student;
import com.shaikhabdulgani.crudrest.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public Student save(Student student){
        student.setStudentId(null);
        return repo.save(student);
    }

    public Student getStudentById(String studentId) throws NotFoundException {
        Optional<Student> student = repo.findById(studentId);
        return student.orElseThrow(
                ()->new NotFoundException("student not found by id "+studentId)
        );
    }

    public Student update(Student student,String studentId) throws NotFoundException {
        if (!repo.existsById(studentId)){
            throw new NotFoundException("student not found by id "+student.getStudentId());
        }
        student.setStudentId(studentId);
        return repo.save(student);
    }

    public String deleteById(String studentId) throws NotFoundException {
        if (!repo.existsById(studentId)){
            throw new NotFoundException("student not found by id "+studentId);
        }
        repo.deleteById(studentId);
        return "deleted student by id "+studentId;
    }

    public String deleteAll(){
        repo.deleteAll();
        return "deleted all entries in database";
    }

    public List<Student> getAll(int pageNumber, int pageSize, SortBy sortBy, Sort.Direction dir){
        Pageable pageable;
        if (sortBy.isNone())
            pageable = PageRequest.of(pageNumber,pageSize,Sort.by(dir,"studentId"));
        else
            pageable = PageRequest.of(pageNumber,pageSize,Sort.by(dir,sortBy.name().toLowerCase()));
        return repo.findAll(pageable).stream().toList();
    }

    public enum SortBy{
        NONE,
        NAME,
        AGE,
        YEAR;

        public boolean isNone(){
            return this.equals(NONE);
        }
    }

}
