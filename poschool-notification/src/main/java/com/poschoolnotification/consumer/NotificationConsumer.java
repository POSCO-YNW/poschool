package com.poschoolnotification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolnotification.domain.dto.NotificationRequest;
import com.poschoolnotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;


    @KafkaListener(topics = "enrollment_history_create", groupId = "notification")
    public void consume(String jsonObject) {
        try {
            NotificationRequest status = objectMapper.readValue(jsonObject, NotificationRequest.class);

            // NotificationService로 상태 정보 전달
            notificationService.processNotificationStatus(status);

        } catch (Exception e) {
            // 예외 처리 로직
        }
    }
}