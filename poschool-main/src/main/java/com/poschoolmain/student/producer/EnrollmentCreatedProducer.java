package com.poschoolmain.student.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnrollmentCreatedProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentCreatedProducer.class);

    public void send(Long studentId, Long courseId) {
        try {
            Map<String, Long> data = new HashMap<>();
            data.put("studentId", studentId);
            data.put("courseId", courseId);

            String jsonPayload = objectMapper.writeValueAsString(data);

            logger.info("send: enrollment_create: " + jsonPayload);

            kafkaTemplate.send("enrollment_create", jsonPayload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
