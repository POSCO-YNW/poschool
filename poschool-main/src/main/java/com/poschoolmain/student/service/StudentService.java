package com.poschoolmain.student.service;

import com.poschoolmain.dto.Notification;
import com.poschoolmain.student.domain.Student;
import com.poschoolmain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Long save(Student student) {
        return studentRepository.save(student);
    }

    public Student findByStudentNumber(String studentNumber) {
        try {
            return studentRepository.findByStudentNumber(studentNumber);
        } catch (Exception e) {
            return null;
        }
    }

    public String requestResult(Notification notification) {
        String message = notification.getCreatedAt() + ", 수강신청 결과: " + notification.getNotificationStatus().getDescription();
        System.out.println(message);
        return message;
    }
}
