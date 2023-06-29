package com.poschoolenrollmentcourse.api;

import com.poschoolenrollmentcourse.domain.Course;
import com.poschoolenrollmentcourse.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<String> processSave(@RequestBody HashMap<String, Object> map) {
        Long saveId = courseService.save(Course.builder()
                .courseName(map.get("courseName").toString())
                .maxCount(Integer.parseInt(map.get("maxCount").toString()))
                .currentCount(0)
                .build());

        return ResponseEntity.ok("과목이 저장되었습니다.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> getAllList() {
        return ResponseEntity.ok().body(courseService.findAll());
    }
}
