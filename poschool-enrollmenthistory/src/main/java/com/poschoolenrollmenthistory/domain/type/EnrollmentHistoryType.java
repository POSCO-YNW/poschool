package com.poschoolenrollmenthistory.domain.type;

public enum EnrollmentHistoryType {
    SUCCESS("성공"), FAIL("실패"), CANCEL("수강취소");
    private String description;

    EnrollmentHistoryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
