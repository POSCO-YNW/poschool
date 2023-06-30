package com.poschoolenrollmenthistory.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolenrollmenthistory.domain.EnrollmentHistory;
import com.poschoolenrollmenthistory.service.EnrollmentHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnrollmentHistoryConsumer {
    private final ObjectMapper objectMapper;
    private final EnrollmentHistoryService enrollmentHistoryService;
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentHistoryConsumer.class);
    @KafkaListener(topics = "enrollment_history_create", groupId = "enrollmenthistory")
    public void consume(String jsonObject) {
        try {
            EnrollmentHistory enrollmentHistory = objectMapper.readValue(jsonObject, EnrollmentHistory.class);
            logger.info("consumer: enrollment_history: " + jsonObject);
            System.out.println("history: " +
                    enrollmentHistory.getEnrollmentId()+" "+
                    enrollmentHistory.getStudentId() +" " +
                    enrollmentHistory.getCourseId() +" "+
                    enrollmentHistory.getSemester() + " " +
                    enrollmentHistory.getEnrollmentStatus());
            enrollmentHistoryService.save(enrollmentHistory);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("history적재에 실패하였습니다");
        }
    }
}
