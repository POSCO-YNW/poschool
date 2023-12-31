package com.poschoolmain.student.domain.type;

public enum EnrollmentStatus {
    REQUEST("신청"), SUCCESS("성공"), FAIL("실패"), CANCEL("취소");

    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
