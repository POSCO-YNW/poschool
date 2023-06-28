package com.poschoolenrollmenthistory.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentHistoryConsumer {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "enrollment_history_create", groupId = "enrollmenthistory")
    public void consume(String jsonObject) {
        try {

        } catch (Exception e) {

        }
    }
}
