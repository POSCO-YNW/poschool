package com.poschoolnotification.service;

import com.poschoolnotification.Repository.NotificationRepository;
import com.poschoolnotification.domain.Notification;
import com.poschoolnotification.domain.dto.NotificationRequest;
import com.poschoolnotification.producer.NotificationCreatedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationKafkaService {

    private final NotificationService notificationService;
    private final NotificationCreatedProducer notificationCreatedProducer;

    public void sendNotification(NotificationRequest request) {
        Notification saveNotification = Notification.builder()
                .studentId(request.getStudentId())
                .courseId(request.getCourseId())
                .notificationStatus(request.getEnrollmentStatus())
                .createdAt(LocalDateTime.now()).build();
                //new Notification(null, request.getStudentId(), request.getCourseId(), request.getNotificationStatus(), createAt);

        notificationService.save(saveNotification);
        notificationCreatedProducer.send(saveNotification);
    }


    //    public void processNotificationStatus(NotificationRequest request) {
//        if(request.getEnrollmentStatus().equals("SUCCESS")){
//            sendSuccessNotification();
//        } else if (request.getEnrollmentStatus().equals("FAIL")){
//            sendFailureNotification();
//        }
//    }
//
//    private void sendSuccessNotification() {
//        // 성공 알림을 처리하는 로직을 구현
//        // 예: 푸시 알림, 이메일 전송 등
//        System.out.println("Success notification sent!");
//    }
//
//    private void sendFailureNotification() {
//        // 실패 알림을 처리하는 로직을 구현
//        // 예: 푸시 알림, 이메일 전송 등
//        System.out.println("Failure notification sent!");
//    }



}
