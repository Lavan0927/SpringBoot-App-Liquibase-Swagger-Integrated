package com.example.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

//StudentRepository interface that extends JpaRepository to handle database operations
public interface StudentRespository extends JpaRepository<Student, Integer> {

}
