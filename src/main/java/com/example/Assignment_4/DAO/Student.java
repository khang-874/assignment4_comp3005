package com.example.Assignment_4.DAO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private int id;
    @Column(name="first_name")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @Column(name="last_name")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @Column(name = "email")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Column(name="enrollment_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date enrollmentDate;
}
