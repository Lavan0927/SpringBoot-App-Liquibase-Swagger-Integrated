package com.example.springboot;

import com.example.springboot.Student;
import com.example.springboot.StudentRespository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/students")
@Api(tags = "Student Management")
public class StudentController {
    @Autowired
    private StudentRespository studentRepository;

    @GetMapping
    @ApiOperation("Get all student record")
    @ApiResponse(code=200, message = "Successfully retrieved student record")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a student by ID")
    @ApiResponses(value = {
           @ApiResponse(code=200, message = "Successfully retreived student by Id"),
            @ApiResponse(code =404, message = "Student not found")
    })
    public Student getStudentById(
            @PathVariable
            @ApiParam(value = "ID of the student to retrieve", example = "1")
            int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ApiOperation("Create a new student record")
    @ApiResponse(code = 201, message = "Successfully created a new student record")
    public Student createStudent(
            @RequestBody
            @ApiParam(value = "Student data to create", required = true)
            Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a student by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update student by ID"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public Student updateStudent(
            @PathVariable
            @ApiParam(value = "ID of the student to update", example = "1")
            int id,
            @RequestBody
            @ApiParam(value = "Updated Student data", required = true)
            Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a student by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted student by ID"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public void deleteStudent(
            @PathVariable
            @ApiParam(value = "ID of the student to delete", example = "1")
            int id) {
        studentRepository.deleteById(id);
    }
}
