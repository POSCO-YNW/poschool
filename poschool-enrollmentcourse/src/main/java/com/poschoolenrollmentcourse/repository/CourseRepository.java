package com.poschoolenrollmentcourse.repository;

import com.poschoolenrollmentcourse.domain.Course;
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
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    public CourseRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Course course) {
        String sql = "INSERT INTO course (course_name, max_count, current_count) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getMaxCount());
            ps.setInt(3, course.getCurrentCount());

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Course findById(Long courseId) {
        String sql = "SELECT * FROM course WHERE course_id = ?";
        return jdbcTemplate.queryForObject(sql, new courseMapper(), courseId);
    }

    public List<Course> findAll() {
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new courseMapper());
    }

    public int updatePlusCurrentCount(Long courseId, int i) {
        String sql = "UPDATE course SET current_count = current_count + ? WHERE course_id = ? AND current_count < max_count;";

        return jdbcTemplate.update(sql, i, courseId);
    }

    public int updateMinusCurrentCount(Long courseId, int i) {
        String sql = "UPDATE course SET current_count = current_count - ? WHERE course_id = ? AND current_count < max_count;";

        return jdbcTemplate.update(sql, i, courseId);
    }

    private static class courseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Course.builder()
                    .courseId(rs.getLong("course_id"))
                    .courseName(rs.getString("course_name"))
                    .maxCount(rs.getInt("max_count"))
                    .currentCount(rs.getInt("current_count"))
                    .build();
        }
    }
}
