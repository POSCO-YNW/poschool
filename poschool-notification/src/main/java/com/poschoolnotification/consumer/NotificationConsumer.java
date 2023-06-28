package com.poschoolnotification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order_state", groupId = "order")
    public void consume(String jsonObject) {
        try {
            //var order = objectMapper.readValue(jsonObject, OrderState.class);
            //orderService.state(order);
        } catch (Exception e) {

        }
    }
}