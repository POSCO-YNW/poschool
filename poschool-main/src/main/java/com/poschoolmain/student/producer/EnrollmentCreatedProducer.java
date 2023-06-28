package com.poschoolmain.student.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
@RequiredArgsConstructor
public class EnrollmentCreatedProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(Long studentId, Long courseId) {
        try {
            Map<String, Long> data = new HashMap<>();
            data.put("studentId", studentId);
            data.put("courseId", courseId);

            String jsonPayload = objectMapper.writeValueAsString(data);

            kafkaTemplate.send("enrollment_create", jsonPayload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
