package com.poschoolnotification.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolnotification.domain.Notification;
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
public class NotificationCreatedProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(NotificationCreatedProducer.class);

    public void send(Notification notification) {
        String jsonObject = null;
        try {
            jsonObject = objectMapper.writeValueAsString(notification);
            logger.info("producer: notification_create: " + jsonObject);
            kafkaTemplate.send("notification_create", jsonObject);
        } catch (Exception e) {

        }
    }

}
