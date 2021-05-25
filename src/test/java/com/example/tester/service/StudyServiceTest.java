package com.example.tester.service;

import com.example.tester.domain.member.Member;
import com.example.tester.domain.study.Study;
import com.example.tester.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * 의존 클래스의 인터페이스는 알고 있지만,
 * 구현체가 없을 경우 예제
 * */
class StudyServiceTest {

    @Test
    void 직접_인터페이스를_구현하여_인스턴스_생성되는지_여부() {
        MemberService memberService = new MemberService() {
            @Override
            public Optional<Member> findById(Long memberId) {
                return Optional.empty();
            }
        };

        StudyRepository studyRepository = new StudyRepository() {
            @Override
            public Study save(Study study) {
                return null;
            }

            @Override
            public List<Study> findAll() {
                return null;
            }

            @Override
            public List<Study> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Study> findAllById(Iterable<Long> iterable) {
                return null;
            }

            @Override
            public <S extends Study> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Study> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<Study> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Study getOne(Long aLong) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Study> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Study> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Study study) {

            }

            @Override
            public void deleteAll(Iterable<? extends Study> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Study> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Study> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Study> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Study> boolean exists(Example<S> example) {
                return false;
            }
        };

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }

    @Test
    void mock을_사용하여_구현하는_인스턴스_생성되는지_여부() {
        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }
}