package com.poschoolenrollmentcourse.service;

import com.poschoolenrollmentcourse.domain.Course;
import com.poschoolenrollmentcourse.domain.Enrollment;
import com.poschoolenrollmentcourse.domain.type.EnrollmentStatus;
import com.poschoolenrollmentcourse.producer.EnrollmentHistoryCreatedProducer;
import com.poschoolenrollmentcourse.repository.CourseRepository;
import com.poschoolenrollmentcourse.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentHistoryCreatedProducer enrollmentHistoryCreatedProducer;

    public void save(Enrollment enrollment) {
        //TODO: 중복 못 하게

        Course course = courseRepository.findById(enrollment.getCourseId());
        int updatedCount = courseRepository.updatePlusCurrentCount(course.getCourseId(), 1);

        if (updatedCount != 0 && enrollment.getEnrollmentStatus().equals(EnrollmentStatus.REQUEST)) {
            enrollment.setEnrollmentStatus(EnrollmentStatus.SUCCESS);
            Long id = enrollmentRepository.save(enrollment);
            enrollment.setEnrollmentId(id);
            enrollmentHistoryCreatedProducer.send(enrollment);
        } else {
            enrollment.setEnrollmentStatus(EnrollmentStatus.FAIL);
            enrollmentHistoryCreatedProducer.send(enrollment);
        }
    }

    public void cancel(Enrollment enrollment) {
        Enrollment findEnrollment = enrollmentRepository.findById(enrollment.getEnrollmentId());

        courseRepository.updateMinusCurrentCount(findEnrollment.getCourseId(), 1);

        enrollmentRepository.delete(findEnrollment.getEnrollmentId());

        findEnrollment.setEnrollmentStatus(EnrollmentStatus.CANCEL);
        enrollmentHistoryCreatedProducer.send(findEnrollment);
    }
}
