package com.example.Assignment_4.Repository;

import com.example.Assignment_4.DAO.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Optional<Student> findOneByEmail(String email);
}
