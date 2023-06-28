package com.poschoolmain.student.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderCreatedProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
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
