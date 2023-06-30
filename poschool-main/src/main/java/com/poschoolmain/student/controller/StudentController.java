package com.poschoolmain.student.controller;

import com.poschoolmain.student.domain.Student;
import com.poschoolmain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/save")
    public String save() {
        return "signup";
    }


    @PostMapping("/save")
    public ResponseEntity<String> processSave(@RequestBody HashMap<String, Object> map) {
        Long saveId = studentService.save(Student.builder()
                .studentName(map.get("studentName").toString())
                .studentNumber(map.get("studentNumber").toString())
                .build());

        return ResponseEntity.ok("Student saved successfully.");
    }

    @PostMapping("/auto/save")
    public ResponseEntity<String> processAutoSave() {
        for (int i = 0; i < 500; i++) {
            studentService.save(Student.builder()
                    .studentName("이름" + i)
                    .studentNumber("학번" + i)
                    .build());
        }

        return ResponseEntity.ok("Student saved successfully.");
    }

//    @PostMapping("/save")
//    public String processSave(@RequestParam("studentName") String studentName,
//                              @RequestParam("studentNumber") String studentNumber) {
//
//        Long saveId = studentService.save(Student.builder()
//                .studentName(studentName)
//                .studentNumber(studentNumber)
//                .build());
//
//        return "redirect:/student/login";
//    }

    @GetMapping("/login")
    public String login() {
        return "pack/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("studentNumber") String studentNumber, HttpSession session) {
        Student student = studentService.findByStudentNumber(studentNumber);
        if (student == null)
            return "redirect:/student/login";
        session.setAttribute("loginStudent", student);

        return "login";
    }
}
