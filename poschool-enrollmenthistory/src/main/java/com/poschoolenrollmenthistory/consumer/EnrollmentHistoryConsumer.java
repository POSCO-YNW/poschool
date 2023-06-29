package com.poschoolenrollmenthistory.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolenrollmenthistory.domain.EnrollmentHistory;
import com.poschoolenrollmenthistory.service.EnrollmentHistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentHistoryConsumer {
    private final ObjectMapper objectMapper;
    private EnrollmentHistoryService enrollmentHistoryService;
//    private static final Logger logger = LoggerFactory.getLogger(EnrollmentHistoryConsumer.class);
    @KafkaListener(topics = "enrollment_history_create", groupId = "enrollmenthistory")
    public void consume(String jsonObject) {
        try {
            var enrollmentHistory = objectMapper.readValue(jsonObject, EnrollmentHistory.class);
            enrollmentHistoryService.save(enrollmentHistory);
        } catch (Exception e) {
            System.out.println("history적재에 실패하였습니다");
        }
    }
}
