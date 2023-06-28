package com.poschoolenrollmentcourse.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolenrollmentcourse.domain.Enrollment;
import com.poschoolenrollmentcourse.domain.type.EnrollmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EnrollmentCreateConsumer {
    //    private final OrderService orderService;
    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "enrollment_create", groupId = "enrollment")
//    public void consume(String jsonPayload) {
//        try {
//            Map<String, Long> data = objectMapper.readValue(jsonPayload, new TypeReference<>() {
//            });
//            Long studentId = data.get("studentId");
//            Long courseId = data.get("courseId");
//
//            Enrollment.builder()
//                    .studentId(studentId)
//                    .courseId(courseId)
//                    .semester("학기")
//                    .build();
//
//            var order = objectMapper.readValue(jsonObject, OrderState.class);
//            orderService.state(order);
//        } catch (Exception e) {
//
//        }
//    }
}
