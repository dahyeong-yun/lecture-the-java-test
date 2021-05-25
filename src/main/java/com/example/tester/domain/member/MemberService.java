package com.example.tester.domain.member;

import com.example.tester.domain.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);
}
