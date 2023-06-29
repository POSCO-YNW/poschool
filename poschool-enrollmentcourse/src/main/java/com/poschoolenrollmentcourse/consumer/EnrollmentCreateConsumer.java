package com.poschoolenrollmentcourse.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolenrollmentcourse.domain.Enrollment;
import com.poschoolenrollmentcourse.domain.type.EnrollmentStatus;
import com.poschoolenrollmentcourse.producer.EnrollmentCreatedProducer;
import com.poschoolenrollmentcourse.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnrollmentCreateConsumer {
    private final EnrollmentService enrollmentService;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentCreateConsumer.class);

    @KafkaListener(topics = "enrollment_create", groupId = "enrollment")
    public void consume(String jsonPayload) {
        try {
            Map<String, Long> data = objectMapper.readValue(jsonPayload, new TypeReference<>() {
            });
            Long studentId = data.get("studentId");
            Long courseId = data.get("courseId");

            logger.info("Listener: enrollment_create: " + jsonPayload);

            Enrollment enrollment = Enrollment.builder()
                    .studentId(studentId)
                    .courseId(courseId)
                    .semester("학기")
                    .build();

//            enrollmentService.state(enrollment);
        } catch (Exception e) {

        }
    }
}
