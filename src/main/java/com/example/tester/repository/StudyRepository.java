package com.example.tester.repository;

import com.example.tester.domain.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
    public Study save(Study study);
}
