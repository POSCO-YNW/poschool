package com.poschoolmain.student.service;

import com.poschoolmain.dto.Enrollment;
import com.poschoolmain.student.producer.EnrollmentCreatedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentKafkaService {
    private final EnrollmentCreatedProducer enrollmentCreatedProducer;

    public void requestCreateEnrollment(Enrollment enrollment) {
        enrollmentCreatedProducer.send(enrollment);
    }
}
