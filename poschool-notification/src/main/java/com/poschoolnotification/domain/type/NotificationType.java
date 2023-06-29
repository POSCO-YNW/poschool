package com.poschoolnotification.domain.type;

public enum NotificationType {
    SUCCESS("성공"), FAIL("실패"), CANCEL("수강취소");
    private String description;

    NotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
