package com.example.tester.service;

import com.example.tester.domain.member.Member;
import com.example.tester.domain.study.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study study);

    void notify(Member member);
}
