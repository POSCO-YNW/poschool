package com.poschoolnotification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;
    private Long studentId;
    private Long courseId;
    private String notificationStatus;

    @Builder
    public Notification(Long notificationId, Long studentId, Long courseId, String notificationStatus) {
        this.notificationId = notificationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.notificationStatus = notificationStatus;
    }
}
