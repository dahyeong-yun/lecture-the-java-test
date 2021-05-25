package com.example.tester.service;

import com.example.tester.domain.member.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);
}
