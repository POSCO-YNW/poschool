package com.poschoolenrollmentcourse.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolenrollmentcourse.domain.Enrollment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnrollmentHistoryCreatedProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentHistoryCreatedProducer.class);

    public void send(Enrollment enrollment) {
        String jsonObject = null;
        try {
            jsonObject = objectMapper.writeValueAsString(enrollment);

            logger.info("producer: enrollment_history_create: " + jsonObject);

            kafkaTemplate.send("enrollment_history_create", jsonObject);
        } catch (Exception e) {
        }
    }
}
