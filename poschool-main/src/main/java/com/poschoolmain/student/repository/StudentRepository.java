package com.poschoolmain.student.repository;

import com.poschoolmain.student.domain.Student;
import com.poschoolmain.student.repository.db.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public Long save(Student student) {
        String sql = "INSERT INTO student (student_name, student_number) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentNumber());

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public StudentRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Student findById(Long studentId) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), studentId);
    }

    public Student findByStudentNumber(String studentNumber) {
        String sql = "SELECT * FROM student WHERE student_number = ?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), studentNumber);
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM Student";
        return jdbcTemplate.query(sql, new StudentMapper());
    }

    public void update(Student student) {
        String sql = "UPDATE student SET student_name = ?, student_number = ? " +
                "WHERE student_id = ?";
        jdbcTemplate.update(sql, student.getStudentName(), student.getStudentNumber(), student.getStudentId());
    }

    public void delete(Long studentId) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        jdbcTemplate.update(sql, studentId);
    }

    private static class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long studentId = rs.getLong("student_id");
            String studentName = rs.getString("student_name");
            String studentNumber = rs.getString("student_number");

            return Student.builder()
                    .studentId(studentId)
                    .studentName(studentName)
                    .studentNumber(studentNumber)
                    .build();
        }
    }
}
