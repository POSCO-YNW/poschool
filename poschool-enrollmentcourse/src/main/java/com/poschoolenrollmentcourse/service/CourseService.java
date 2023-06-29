package com.poschoolenrollmentcourse.service;

import com.poschoolenrollmentcourse.domain.Course;
import com.poschoolenrollmentcourse.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Long save(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id);
    }
}
