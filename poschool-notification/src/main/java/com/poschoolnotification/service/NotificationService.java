package com.poschoolnotification.service;

import com.poschoolnotification.consumer.NotificationConsumer;
import com.poschoolnotification.domain.dto.NotificationRequest;
import com.poschoolnotification.domain.type.NotificationType;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void processNotificationStatus(NotificationRequest request) {
        if(request.getEnrollmentStatus().equals("SUCCESS")){
            sendSuccessNotification();
        } else if (request.getEnrollmentStatus().equals("FAIL")){
            sendFailureNotification();
        }
    }

    private void sendSuccessNotification() {
        // 성공 알림을 처리하는 로직을 구현
        // 예: 푸시 알림, 이메일 전송 등
        System.out.println("Success notification sent!");
    }

    private void sendFailureNotification() {
        // 실패 알림을 처리하는 로직을 구현
        // 예: 푸시 알림, 이메일 전송 등
        System.out.println("Failure notification sent!");
    }
}
