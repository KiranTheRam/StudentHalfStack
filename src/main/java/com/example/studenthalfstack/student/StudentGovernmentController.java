package com.example.studenthalfstack.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("government/student")
public class StudentGovernmentController {

    private final StudentService studentService;

    @Autowired
    public StudentGovernmentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = "/all")
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Optional<Student> getStudent(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public void registerNewStudent (@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent (@PathVariable("studentId") Long studentId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email ) {
        studentService.updateStudent(studentId, name, email);
    }
}
