package com.shaikhabdulgani.crudrest.service;

import com.shaikhabdulgani.crudrest.exception.NotFoundException;
import com.shaikhabdulgani.crudrest.model.Student;
import com.shaikhabdulgani.crudrest.repo.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ContextConfiguration(classes = { StudentServiceTest.class })
@RunWith(SpringRunner.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService service;

    @Mock
    private StudentRepo repo;

    private Student student;

    private List<Student> students;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        student = Student.builder()
                .email("shkhabdulganu@gmail.com")
                .age(19)
                .name("abdul gani")
                .year(Student.Year.FE)
                .build();

        students = new ArrayList<>();
        students.add(
                Student.builder()
                        .studentId("2")
                        .email("1231@gmail.com")
                        .age(18)
                        .name("tehgan")
                        .year(Student.Year.SE)
                        .build()
        );

        students.add(
                Student.builder()
                        .studentId("1")
                        .email("shkhabdulganu@gmail.com")
                        .age(11)
                        .name("abdul gani")
                        .year(Student.Year.TE)
                        .build()
        );

        students.add(
                Student.builder()
                        .studentId("3")
                        .email("shkhabdulganu@gmail.com")
                        .age(12)
                        .name("shaikh")
                        .year(Student.Year.BE)
                        .build()
        );

        students.add(
                Student.builder()
                        .studentId("4")
                        .email("shkhabdulganu@gmail.com")
                        .age(34)
                        .name("abdul")
                        .year(Student.Year.BE)
                        .build()
        );

        students.add(
                Student.builder()
                        .studentId("5")
                        .email("shkhabdulganu@gmail.com")
                        .age(45)
                        .name("gani")
                        .year(Student.Year.FE)
                        .build()
        );

    }

    @Test
    void testSave() {

        Mockito.when(repo.save(any())).thenReturn(student);
        Student actual = service.save(student);
        assertThat(actual).isEqualTo(student);

    }

    @Test
    void test_getStudentById_Provide_Wrong_Id() {

        Mockito.when(repo.findById(any())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(NotFoundException.class,
                ()->{
                    service.getStudentById("wrong id");
                });

        assertEquals(exception.getClass(), NotFoundException.class);

    }

    @Test
    void test_getStudentById_Provide_Right_Id() throws NotFoundException {

        Mockito.when(repo.findById(any())).thenReturn(Optional.of(student));
        Student actual = service.getStudentById("right id");

        assertEquals(actual,student);

    }

    @Test
    void test_GetStudentById_Provide_No_Id() {

        Mockito.when(repo.findById(any())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(NotFoundException.class,
                ()->{
                    service.getStudentById(null);
                });

        assertEquals(exception.getClass(), NotFoundException.class);

    }

    @Test
    void test_update_withWrongId() {

        Mockito.when(repo.existsById(any())).thenReturn(false);
        Throwable exception = assertThrows(NotFoundException.class,
                ()->{
                    service.update(student,"wrong id");
                });

        assertEquals(exception.getClass(), NotFoundException.class);

    }

    @Test
    void test_update_withRightId() throws NotFoundException {

        Mockito.when(repo.existsById(any())).thenReturn(true);
        Mockito.when(repo.save(any())).thenReturn(student);
        Student actual = service.update(student,"rightId");

        assertEquals(actual, student);

    }

    @Test
    void test_delete_withWrongId() {

        Mockito.when(repo.existsById(any())).thenReturn(false);
        Throwable exception = assertThrows(NotFoundException.class,
                ()->{
                    service.deleteById("wrong id");
                });

        assertEquals(exception.getClass(), NotFoundException.class);

    }

    @Test
    void test_delete_withRightId() throws NotFoundException {

        Mockito.when(repo.existsById(any())).thenReturn(true);
        String actual = service.deleteById("right id");
        assertFalse(actual.isBlank());

    }

    @Test
    void deleteAll() {
        String actual = service.deleteAll();
        assertFalse(actual.isBlank());
    }
}