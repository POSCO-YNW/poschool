package com.poschoolmain.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
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
