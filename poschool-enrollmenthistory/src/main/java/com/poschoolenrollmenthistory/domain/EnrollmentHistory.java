package com.poschoolenrollmenthistory.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class EnrollmentHistory {
    private Long enrollment_id;
    private Long student_id;
    private Long course_id;
    private String Semester;
    private String Enrollment_history_status;
    @Builder
    public EnrollmentHistory(Long enrollment_id, Long student_id, Long course_id, String semester, String enrollment_history_status) {
        this.enrollment_id = enrollment_id;
        this.student_id = student_id;
        this.course_id = course_id;
        Semester = semester;
        Enrollment_history_status = enrollment_history_status;
    }
}
