package com.poschoolenrollmentcourse.domain;

import com.poschoolenrollmentcourse.domain.type.EnrollmentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Enrollment {
    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private String semester;
    private EnrollmentStatus enrollmentStatus;

    @Builder
    public Enrollment(Long enrollmentId, Long studentId, Long courseId, String semester, EnrollmentStatus enrollmentStatus) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.enrollmentStatus = enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
}
