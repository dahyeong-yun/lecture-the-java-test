package com.example.tester.service;

import com.example.tester.domain.member.Member;
import com.example.tester.domain.study.Study;
import com.example.tester.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Mock stubbing 연습 문제
 * */
@ExtendWith(MockitoExtension.class)
public class StudyServiceMockExam {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void 연습문제_제출() {
        Study study = new Study(10, "테스트");

        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        StudyService studyService = new StudyService(memberService, studyRepository);

        studyService.createNewStudy(1L, study);
        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }

    @Test
    void 연습문제_답안(@Mock MemberService memberServiceParam, @Mock StudyRepository studyRepositoryParam) {
        StudyService studyService = new StudyService(memberServiceParam, studyRepositoryParam);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        Study study = new Study(10, "테스트");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);
        assertEquals(member, study.getOwner());
    }
}