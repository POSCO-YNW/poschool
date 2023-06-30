package com.poschoolmain.student.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolmain.dto.Notification;
import com.poschoolmain.student.producer.EnrollmentCreatedProducer;
import com.poschoolmain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationCreateConsumer {
    private final ObjectMapper objectMapper;
    private final StudentService studentService;

    private static final Logger logger = LoggerFactory.getLogger(NotificationCreateConsumer.class);

    @KafkaListener(topics = "notification_create", groupId = "student")
    public void consume(ConsumerRecord<String, String> record) {
        try {

            var notification = objectMapper.readValue(record.value(), Notification.class);

            logger.info("consumer: notification_create -, Partition: {}, Offset: {}: {}",
                    record.partition(), record.offset(), record.value());

            studentService.requestResult(notification);
        } catch (Exception e) {

        }
    }
}
