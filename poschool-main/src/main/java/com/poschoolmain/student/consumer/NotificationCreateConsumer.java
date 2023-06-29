package com.poschoolmain.student.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poschoolmain.student.producer.EnrollmentCreatedProducer;
import com.poschoolmain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void consume(String jsonObject) {
        try {
            // JSON 파싱
            JSONObject parsing = new JSONObject(jsonObject);

            // "message" 필드의 값 가져오기
            String message = parsing.getString("message");

            logger.info("consumer: notification_create: " + message);

            studentService.requestResult(message);
        } catch (Exception e) {

        }
    }
}
