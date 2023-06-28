package com.poschoolmain.student.controller;

import com.poschoolmain.student.domain.Student;
import com.poschoolmain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/save")
    public String save() {
        return "signup";
    }

    @PostMapping("/save")
    public String processSave(@RequestParam("studentName") String studentName,
                              @RequestParam("studentNumber") String studentNumber) {

        Long saveId = studentService.save(Student.builder()
                .studentName(studentName)
                .studentNumber(studentNumber)
                .build());

        return "redirect:/student/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("studentNumber") String studentNumber, HttpSession session) {
        Student student = studentService.findByStudentNumber(studentNumber);
        if (student == null)
            return "redirect:/student/login";
        session.setAttribute("loginStudent", student);

        return "course/list";
    }
}
