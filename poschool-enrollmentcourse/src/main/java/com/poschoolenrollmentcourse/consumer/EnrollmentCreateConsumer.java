package com.poschoolenrollmentcourse.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolenrollmentcourse.domain.Enrollment;
import com.poschoolenrollmentcourse.domain.type.EnrollmentStatus;
import com.poschoolenrollmentcourse.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
    public void consume(ConsumerRecord<String, String> record) {
        try {
            var enrollment = objectMapper.readValue(record.value(), Enrollment.class);

            logger.info("consumer: enrollment_create -, Partition: {}, Offset: {}: {}",
                    record.partition(), record.offset(), record.value());

            if (enrollment.getEnrollmentStatus().equals(EnrollmentStatus.REQUEST)) {
                enrollmentService.save(enrollment);
            } else if (enrollment.getEnrollmentStatus().equals(EnrollmentStatus.CANCEL)) {
                enrollmentService.cancel(enrollment);
            }
        } catch (Exception e) {

        }
    }
}
