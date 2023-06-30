package com.poschoolenrollmenthistory.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
public class EnrollmentHistory {
    @Builder
    public EnrollmentHistory(Long enrollmentId, Long studentId, Long courseId, String semester, String enrollmentStatus) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.enrollmentStatus = enrollmentStatus;
    }

    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private String semester;
    //    private String enrollment_history_status;
    private String enrollmentStatus;


    //    private Date created_at;
//    @Builder
//    public EnrollmentHistory(Long enrollment_id, Long student_id, Long course_id, String semester, String enrollment_history_status, Date created_at) {
//        this.enrollment_id = enrollment_id;
//        this.student_id = student_id;
//        this.course_id = course_id;
//        this.semester = semester;
//        this.enrollment_history_status = enrollment_history_status;
//        this.created_at = created_at;
//    }

}
