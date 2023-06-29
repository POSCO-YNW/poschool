package com.poschoolenrollmentcourse.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class EnrollmentCreatedProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentCreatedProducer.class);
//    private final FailRepository failRepository;

//    public void send(Orders orders) {
//        String jsonObject = null;
//        try {
//            jsonObject = objectMapper.writeValueAsString(new Order(orders.getId(), orders.getUserId(), orders.getStoreId(), orders.getPrice()));
//
//            if (orders.getUserId() == 2L) throw new Exception();
//
//            kafkaTemplate.send("order_create", jsonObject);
//        } catch (Exception e) {
//            failRepository.save(FailEvent.of(jsonObject));
//        }
//    }
}
