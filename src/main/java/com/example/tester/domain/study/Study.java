package com.example.tester.domain.study;

import com.example.tester.domain.member.Member;
import com.example.tester.domain.study.StudyStatus;

import java.time.LocalDateTime;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;
    private Member owner;
    private String name;
    private LocalDateTime openedDateTime;

    public Study() {}

    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit는 0 이상이어야 합니다.");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }


    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return this.limit;
    }

    public Member getOwner() {
        return this.owner;
    }

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
    }

    public void setOwner(Member member) {
        this.owner = member;
    }

    public void open() {
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }
}
