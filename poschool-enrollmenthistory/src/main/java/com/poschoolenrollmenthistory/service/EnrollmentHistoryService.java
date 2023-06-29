package com.poschoolenrollmenthistory.service;

import com.poschoolenrollmenthistory.domain.EnrollmentHistory;
import com.poschoolenrollmenthistory.repository.EnrollmentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentHistoryService {
    private final EnrollmentHistoryRepository enrollmentHistoryRepository;

    public Long save(EnrollmentHistory enrollmentHistory){
//        return enrollmentHistoryRepository.save(new EnrollmentHistory(
//                null,
//                enrollmentHistory.getStudent_id(),
//                enrollmentHistory.getCourse_id(),
//                enrollmentHistory.getSemester(),
//                enrollmentHistory.getEnrollment_history_status()
//        ));
        return enrollmentHistoryRepository.save(enrollmentHistory);
    }
}
