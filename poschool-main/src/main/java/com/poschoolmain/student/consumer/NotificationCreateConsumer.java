package com.poschoolmain.student.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentStateConsumer {
//    private final OrderService orderService;
    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "enrollment_state", groupId = "student")
//    public void consume(String jsonObject) {
//        try {
//            var order = objectMapper.readValue(jsonObject, OrderState.class);
//            orderService.state(order);
//        } catch (Exception e) {
//
//        }
//    }
}
