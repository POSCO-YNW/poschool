package com.poschoolenrollmenthistory.repository;

import com.poschoolenrollmenthistory.domain.EnrollmentHistory;
import com.poschoolenrollmenthistory.repository.db.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository
public class EnrollmentHistoryRepository {
    private final JdbcTemplate jdbcTemplate;
    public EnrollmentHistoryRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(EnrollmentHistory enrollmentHistory){
        String sql = "INSERT INTO enrollment_history (student_id, course_id, semester, enrollment_history_status, created_at) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime created_at = LocalDateTime.now();
        System.out.println("date: " + created_at);
        System.out.println(enrollmentHistory.getStudentId() + " " + enrollmentHistory.getCourseId());
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, enrollmentHistory.getStudentId());
            ps.setLong(2, enrollmentHistory.getCourseId());
            ps.setString(3, enrollmentHistory.getSemester());
            ps.setString(4, enrollmentHistory.getEnrollmentStatus());
            ps.setString(5, String.valueOf(created_at));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
