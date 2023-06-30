package com.poschoolmain.dto;

import com.poschoolmain.dto.type.EnrollmentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {
    private Long notificationId;
    private Long studentId;
    private Long courseId;
    private EnrollmentStatus notificationStatus;
    private LocalDateTime createdAt;

    @Builder
    public Notification(Long notificationId, Long studentId, Long courseId, EnrollmentStatus notificationStatus, LocalDateTime createdAt) {
        this.notificationId = notificationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.notificationStatus = notificationStatus;
        this.createdAt = createdAt;
    }
}