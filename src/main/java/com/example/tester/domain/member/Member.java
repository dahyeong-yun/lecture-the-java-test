package com.example.tester.domain.member;

public class Member {
    private Long id;
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
