package com.poschoolenrollmentcourse.domain.type;

public enum EnrollmentStatus {
    SUCCESS("성공"), FAIL("실패"), CANCEL("취소"), WAITING("대기");

    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
