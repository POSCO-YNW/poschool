package com.poschoolnotification.domain.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private String semester;
    private String enrollmentStatus;
}
