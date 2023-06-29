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
        Long saveId = enrollmentHistoryService.save(EnrollmentHistory.builder()
                        .course_id(Long.valueOf(map.get("courseId").toString()))
                        .student_id(Long.valueOf(map.get("studentId").toString()))
                        .semester(map.get("semester").toString())
                        .enrollment_history_status(map.get("enrollmentHistoryStatus").toString())
                        .build());

        return ResponseEntity.ok("신청 내역에 대한 로그가 저장되었습니다.");
    }
}
