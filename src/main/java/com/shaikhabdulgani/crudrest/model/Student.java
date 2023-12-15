package com.shaikhabdulgani.crudrest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String studentId;

    @Column(nullable = false)
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "year cannot be null")
    private Year year;

    @Column(nullable = false,unique = true)
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    private String email;

    @Column(nullable = false)
    @Min(value = 10,message = "age cannot be less than 10")
    @Max(value = 99,message = "age cannot be more than 99")
    private int age;

    public enum Year{
        FE,
        SE,
        TE,
        BE,
    }

}
