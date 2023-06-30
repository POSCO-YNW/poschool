package com.poschoolnotification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

    @Builder
    public Notification(Long notificationId, Long studentId, Long courseId, String notificationStatus, LocalDateTime createdAt) {
        this.notificationId = notificationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.notificationStatus = notificationStatus;
        this.createdAt = createdAt;
    }
}
