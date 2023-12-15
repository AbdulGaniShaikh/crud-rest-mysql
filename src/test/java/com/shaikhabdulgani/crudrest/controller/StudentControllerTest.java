package com.shaikhabdulgani.crudrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaikhabdulgani.crudrest.exception.NotFoundException;
import com.shaikhabdulgani.crudrest.model.Student;
import com.shaikhabdulgani.crudrest.service.StudentService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Student studentNoId;
    private Student studentId;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        studentNoId = Student.builder()
                .email("shkhabdulganu@gmail.com")
                .age(19)
                .name("abdul gani")
                .year(Student.Year.FE)
                .build();

        studentId = Student.builder()
                .studentId("1")
                .email("shkhabdulganu@gmail.com")
                .age(19)
                .name("abdul gani")
                .year(Student.Year.FE)
                .build();

    }

    @Test
    void saveStudent_ReturnStudent() throws Exception{

        when(service.save(any())).thenReturn(studentId);
        ResultActions response = mockMvc.perform(post("/v1/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentNoId))
                .characterEncoding(StandardCharsets.UTF_8)
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(studentId.getName()))
                )
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void saveStudent_ReturnError() throws Exception{

        when(service.save(any())).thenReturn(studentId);
        ResultActions response = mockMvc.perform(post("/v1/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Student()))
                .characterEncoding(StandardCharsets.UTF_8)
        );

        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void getStudent_ReturnStudent() throws Exception {

        when(service.getStudentById(anyString())).thenReturn(studentId);
        ResultActions response = mockMvc.perform(get("/v1/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        );

        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(studentId.getName())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void getStudent_ReturnError() throws Exception {

        when(service.getStudentById(anyString())).thenThrow(
                new NotFoundException("student not found by id "+"1")
        );
        ResultActions response = mockMvc.perform(get("/v1/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        );

        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void updateStudent_ReturnUpdatedStudent() throws Exception {

        when(service.update(any(),any())).thenReturn(studentId);
        ResultActions response = mockMvc.perform(put("/v1/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(studentNoId))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk()
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(studentNoId.getName()))
                )
                .andDo(
                MockMvcResultHandlers.print()
        );
    }

    @Test
    void updateStudent_ReturnError() throws Exception {

        when(service.update(any(),any())).thenThrow(new NotFoundException("student not found by id "+"1"));
        ResultActions response = mockMvc.perform(put("/v1/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(studentNoId))
        );

        response.andExpect(MockMvcResultMatchers.status().isNotFound()
                )
                .andDo(
                        MockMvcResultHandlers.print()
                );
    }

    @Test
    void deleteStudent_ReturnSuccess() throws Exception {
        when(service.deleteById(any())).thenReturn("Success");

        ResultActions response = mockMvc.perform(delete("/v1/api/students/1")
                .characterEncoding(StandardCharsets.UTF_8)
        );

        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()
                )
                .andDo(
                        MockMvcResultHandlers.print()
                );
    }

    @Test
    void deleteStudent_ReturnError() throws Exception {
        when(service.deleteById(any())).thenThrow(new NotFoundException("student not found by id "+"1"));

        ResultActions response = mockMvc.perform(delete("/v1/api/students/1")
                .characterEncoding(StandardCharsets.UTF_8)
        );

        response.andExpect(MockMvcResultMatchers.status().isNotFound()
                )
                .andDo(
                        MockMvcResultHandlers.print()
                );
    }

    @Test
    void deleteAllStudent_ReturnSuccess() throws Exception {
        when(service.deleteAll()).thenReturn("Success");

        ResultActions response = mockMvc.perform(delete("/v1/api/students")
                .characterEncoding(StandardCharsets.UTF_8)
        );

        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()
                )
                .andDo(
                        MockMvcResultHandlers.print()
                );
    }
}