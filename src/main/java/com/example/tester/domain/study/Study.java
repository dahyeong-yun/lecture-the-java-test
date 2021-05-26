package com.example.tester.domain.study;

import com.example.tester.domain.member.Member;
import com.example.tester.domain.study.StudyStatus;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;
    private Member owner;
    private String name;

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

    public void setOwner(Member member) {
        this.owner = member;
    }

    public Member getOwner() {
        return this.owner;
    }
}
