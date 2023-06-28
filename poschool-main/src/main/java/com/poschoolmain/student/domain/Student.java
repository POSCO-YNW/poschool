package com.poschoolmain.student.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Student {
    private Long studentId;

    private String studentName;

    private String studentNumber;

    @Builder
    public Student(Long studentId, String studentName, String studentNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
    }
}
