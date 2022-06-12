package com.example.studenthalfstack.student;

import com.example.studenthalfstack.securty.ApplicationUserRole;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.studenthalfstack.securty.ApplicationUserRole.STUDENT;

@RestController
@RequestMapping("management/student")
public class StudentManagementController  {
    private static final List<Student> students = Arrays.asList(
            new Student("Mike", "mike@email.com", 30),
            new Student("Quinn", "quinn@email.com", 92),
            new Student("Anna", "ana@email.com", 32)
    );

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        System.out.println(id);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id, @RequestBody Student student) {
        System.out.println(id);
    }
}
