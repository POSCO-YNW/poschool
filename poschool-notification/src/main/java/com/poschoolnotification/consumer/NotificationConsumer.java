package com.poschoolnotification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolnotification.domain.dto.NotificationRequest;
import com.poschoolnotification.producer.NotificationCreatedProducer;
import com.poschoolnotification.service.NotificationKafkaService;
import com.poschoolnotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final ObjectMapper objectMapper;
    private final NotificationKafkaService notificationKafkaService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationCreatedProducer.class);

    @KafkaListener(topics = "enrollment_history_create", groupId = "notification")
    public void consume(String jsonObject) {
        try {
            NotificationRequest status = objectMapper.readValue(jsonObject, NotificationRequest.class);
            logger.info("consumer: enrollment_history_create: " + jsonObject);

            // NotificationService로 상태 정보 전달
            notificationKafkaService.sendNotification(status);

        } catch (Exception e) {
            // 예외 처리 로직
        }
    }
}