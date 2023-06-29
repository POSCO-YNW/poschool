package com.poschoolenrollmentcourse.repository;

import com.poschoolenrollmentcourse.domain.Course;
import com.poschoolenrollmentcourse.domain.Enrollment;
import com.poschoolenrollmentcourse.domain.type.EnrollmentStatus;
import com.poschoolenrollmentcourse.repository.db.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class EnrollmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (student_id, course_id, Semester, enrollment_status) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, enrollment.getStudentId());
            ps.setLong(2, enrollment.getCourseId());
            ps.setString(3, enrollment.getSemester());
            ps.setString(4, String.valueOf(enrollment.getEnrollmentStatus()));

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Enrollment findById(Long enrollmentId) {
        String sql = "SELECT * FROM enrollment WHERE enrollment_id = ?";
        return jdbcTemplate.queryForObject(sql, new enrollmentMapper(), enrollmentId);
    }

    public List<Enrollment> findAll() {
        String sql = "SELECT * FROM enrollment";
        return jdbcTemplate.query(sql, new enrollmentMapper());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM enrollment WHERE enrollment_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class enrollmentMapper implements RowMapper<Enrollment> {
        @Override
        public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Enrollment.builder()
                    .enrollmentId(rs.getLong("enrollment_id"))
                    .studentId(rs.getLong("student_id"))
                    .courseId(rs.getLong("course_id"))
                    .semester(rs.getString("semester"))
                    .enrollmentStatus(EnrollmentStatus.valueOf(rs.getString("enrollemnet_status")))
                    .build();
        }
    }

}
