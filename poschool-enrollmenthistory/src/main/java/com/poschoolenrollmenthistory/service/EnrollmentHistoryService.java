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
        return enrollmentHistoryRepository.save(enrollmentHistory);
    }
}
