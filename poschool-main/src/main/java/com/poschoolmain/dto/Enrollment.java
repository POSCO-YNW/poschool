package com.poschoolmain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Enrollment {
    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private String semester;
    private String enrollmentStatus;

    @Builder
    public Enrollment(Long enrollmentId, Long studentId, Long courseId, String semester, String enrollmentStatus) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.enrollmentStatus = enrollmentStatus;
    }
}
