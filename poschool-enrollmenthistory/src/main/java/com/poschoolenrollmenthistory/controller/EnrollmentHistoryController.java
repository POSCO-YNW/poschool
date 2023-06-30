package com.poschoolenrollmenthistory.controller;

import com.poschoolenrollmenthistory.domain.EnrollmentHistory;
import com.poschoolenrollmenthistory.service.EnrollmentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

// controller 필요없을듯?
@RestController
@RequiredArgsConstructor
public class EnrollmentHistoryController {
    private final EnrollmentHistoryService enrollmentHistoryService;
    @PostMapping("/savehistory")
    public ResponseEntity<String> saveHistory(@RequestBody HashMap<String, Object> map) {
        Long saveId = enrollmentHistoryService.save(EnrollmentHistory.builder()
                        .courseId(Long.valueOf(map.get("courseId").toString()))
                        .studentId(Long.valueOf(map.get("studentId").toString()))
                        .semester(map.get("semester").toString())
                        .enrollmentStatus(map.get("enrollmentHistoryStatus").toString())
                        .build());

        return ResponseEntity.ok("신청 내역에 대한 로그가 저장되었습니다.");
    }
}
