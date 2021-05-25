package com.example.tester.service;

import com.example.tester.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
