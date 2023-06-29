package com.poschoolenrollmenthistory.controller;

import com.poschoolenrollmenthistory.domain.EnrollmentHistory;
import com.poschoolenrollmenthistory.service.EnrollmentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class EnrollmentHistoryController {
    private final EnrollmentHistoryService enrollmentHistoryService;
    @PostMapping("/savehistory")
    public ResponseEntity<String> saveHistory(@RequestBody HashMap<String, Object> map) {
//        Long saveId = enrollmentHistoryService.save(EnrollmentHistory.builder()
//                        .course_id(map.get("courseId").toString())
//                        .student_id(map.get("studentId").toString())
//                        .semester(map.get("semester").toString())
//                        .enrollment_history_status(map.get("enrollmentHistoryStatus").toString())
//                        .build());
//                .courseName(map.get("courseName").toString())
//                .maxCount(Integer.parseInt(map.get("maxCount").toString()))
//                .currentCount(0)
//                .build());

        return ResponseEntity.ok("로그가 저장되었습니다.");
    }
}
