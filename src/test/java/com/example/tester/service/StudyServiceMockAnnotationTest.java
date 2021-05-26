package com.example.tester.service;

import com.example.tester.domain.member.Member;
import com.example.tester.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudyServiceMockAnnotationTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void 어노테이션으로_mock_만들어서_인스턴스_생성되는지_여부() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    @Test
    void 파라미터로_mock을만들어서_인스턴스_생성되는지_여부(@Mock MemberService memberServiceParam, @Mock StudyRepository studyRepositoryParam) {
        StudyService studyService = new StudyService(memberServiceParam, studyRepositoryParam);
        assertNotNull(studyService);
    }

    @Test
    void 객체_stubbing_equal_테스트() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        when(memberService.findById(1L)).thenReturn(Optional.of(member)); // stubbing
        // when(memberService.findById(2L)).thenReturn(Optional.of(member)); // 2L로 주면 테스트가 실패
        // when(memberService.findById(any())).thenReturn(Optional.of(member)); // 아무 값이나 오게 할 수도 있음


        Optional<Member> findById = memberService.findById(1L);
        assertEquals("gryb809@gmail.com", findById.get().getEmail());
    }

    @Test
    void 객체_stubbing_throw_테스트() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
        // when(memberService.findById(1L)).thenThrow(new RuntimeException()); // 에외가 나오게 할 수 있음

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });
    }

    @Test
    void 여러번_호출시마다_다른_값을_주기() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        when(memberService.findById(1L))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> findById = memberService.findById(1L);
        assertEquals("gryb809@gmail.com", findById.get().getEmail());
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(1L);
        });
        assertEquals(Optional.empty(), memberService.findById(1L));
    }
}