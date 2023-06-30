package com.poschoolnotification.Repository;

import com.poschoolnotification.domain.Notification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {
    private final JdbcTemplate jdbcTemplate;

    public NotificationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Notification notification) {
        String query = "INSERT INTO notification (notification_id, student_id, course_id, notification_status, created_at) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, notification.getNotificationId(), notification.getStudentId(),
                notification.getCourseId(), notification.getNotificationStatus(), notification.getCreatedAt());
    }
}
