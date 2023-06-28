package com.poschoolenrollmenthistory.repository;

import com.poschoolenrollmenthistory.domain.EnrollmentHistory;
import com.poschoolenrollmenthistory.repository.db.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class EnrollmentHistoryRepository {
    private final JdbcTemplate jdbcTemplate;
    public EnrollmentHistoryRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(EnrollmentHistory enrollmentHistory){
        String sql = "INSERT INTO enrollment_history (student_id, course_id, semester, enrollment_history_status) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, enrollmentHistory.getStudent_id());
            ps.setLong(2, enrollmentHistory.getCourse_id());
            ps.setString(3, enrollmentHistory.getSemester());
            ps.setString(4, enrollmentHistory.getEnrollment_history_status());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
