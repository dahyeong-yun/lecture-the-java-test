package com.example.tester.service;

import com.example.tester.domain.member.Member;
import com.example.tester.domain.study.Study;
import com.example.tester.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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

    @Test
    void 특정_메소드가_호출되었는지_확인(@Mock MemberService memberServiceParam, @Mock StudyRepository studyRepositoryParam) {
        StudyService studyService = new StudyService(memberServiceParam, studyRepositoryParam);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        Study study = new Study(10, "테스트");

        when(memberServiceParam.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepositoryParam.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        verify(memberServiceParam, times(1)).notify(study); // 몇번 호출되었느냐의 의미
        verify(memberServiceParam, never()).validate(any());
    }

    @Test
    void 특정_메소드가_순차적으로_호출되었는지_확인(@Mock MemberService memberServiceParam, @Mock StudyRepository studyRepositoryParam) {
        StudyService studyService = new StudyService(memberServiceParam, studyRepositoryParam);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        Study study = new Study(10, "테스트");

        when(memberServiceParam.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepositoryParam.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        InOrder inOrder = inOrder(memberServiceParam);
        inOrder.verify(memberServiceParam).notify(study); // 언제 무엇이 발생했느냐의 의미
        inOrder.verify(memberServiceParam).notify(member);

        // verifyNoMoreInteractions(memberServiceParam); // 이후 한번도 호출되지 않아야 할 때
    }

    @Test
    void Mockito_BDD_API를_사용했을_경우(@Mock MemberService memberServiceParam, @Mock StudyRepository studyRepositoryParam) {
        // given
        StudyService studyService = new StudyService(memberServiceParam, studyRepositoryParam);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("gryb809@gmail.com");

        Study study = new Study(10, "테스트");

        given(memberServiceParam.findById(1L)).willReturn(Optional.of(member));
        given(studyRepositoryParam.save(study)).willReturn(study);

        // when
        studyService.createNewStudy(1L, study);

        // then
        InOrder inOrder = inOrder(memberServiceParam);
        then(memberServiceParam).should(times(1)).notify(study);
        // then(memberServiceParam).shouldHaveNoInteractions();
    }
}