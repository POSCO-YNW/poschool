package com.poschoolnotification.service;

import com.poschoolnotification.Repository.NotificationRepository;
import com.poschoolnotification.consumer.NotificationConsumer;
import com.poschoolnotification.domain.Notification;
import com.poschoolnotification.domain.dto.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
}
