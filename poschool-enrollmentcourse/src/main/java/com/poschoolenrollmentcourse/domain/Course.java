package com.poschoolenrollmentcourse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Course {
    private Long courseId;
    private String courseName;
    private Integer maxCount;
    private Integer currentCount;

    @Builder
    public Course(Long courseId, String courseName, Integer maxCount, Integer currentCount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxCount = maxCount;
        this.currentCount = currentCount;
    }
}
