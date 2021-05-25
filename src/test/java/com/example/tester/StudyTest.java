package com.example.tester;

import com.example.tester.domain.Study;
import com.example.tester.domain.study.StudyStatus;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {


    /**
     * message 파라미터에 String 객체를 보내는 경우,
     * 메세지 객체가 필요하지 않아도 String 객체를 생성
     */
    @Test
    void 같은_값인지_테스트_하는_방법1() {
        Study study =  new Study();
        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디의 기본 상태는 DRAFT");
    }

    /**
     * message 파라미터에 Supplier 객체를 보내는 경우,
     * 메세지가 필요할 경우만 생성
     * */
    @Test
    void 같은_값인지_테스트_하는_방법2() {
        Study study =  new Study();
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디의 기본 상태는 DRAFT");
    }

    @Test
    void 널_깂인지_테스트_하는_방법() {
        Study study =  new Study();
        assertNotNull(study);
    }

    /**
     * 테스트를 여러 개 그냥 넣을 경우 가장 먼저 실패하는 곳이 있으면 이하의 테스트를 수행하지 않는다.
     * 그렇게 되면, 모든 테스트가 다 실패하는 경우에 테스트의 숫자만큼 다시 확인을 해야한다.
     * assertAll을 사용하면 이같은 불편을 없애고 한번 테스트 시점에 실패하는 테스트가 무엇인지 다 알 수 있다.
     * */
    @Test
    void 한번에_많은_테스트를_하는_방법() {
        Study study =  new Study(-10);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디의 기본 상태는 DRAFT"),
                () -> assertTrue(study.getLimit() > 0, "스터디의 기본 상태는 DRAFT")
        );
    }

    @Test
    void 예상한_예외가_발생하는지_테스트_하는_방법() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit는 0 이상이어야 합니다.", e.getMessage());
    }

    /**
     * 전체 실행 시간을 죽 기다린 다음에 테스트 하고자 했던 시간과 비교하는 방법
     * */
    @Test
    void 걸리는_시간을_테스트_하는_방법1() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    /**
     * 테스트 하고자 했던 시간을 넘기면 바로 실패를 날리는 방법
     * 이때는 별개의 스레드로 작동하기 떄문에, 테스트 안의 로직이 예상치 못한 실행 결과를 가져 올 수 있다.
     * */
    @Test
    void 걸리는_시간을_테스트_하는_방법2() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }
    
    @Test
    @Disabled // 이 테스트를 무시하고 실행된다.
    void create1() {
        System.out.println("create1");
    }

    /**
     * 전체 테스트 이전에 딱 한번만 실행된다.
     * */
    @BeforeAll
    static void beforeAll() {
        System.out.println("==before all==");
    }

    /**
     * 전체 테스트 이후에 딱 한번만 실행된다.
     * */
    @AfterAll
    static void afterAll() {
        System.out.println("==after all==");
    }

    /**
     * 개별 테스트 이전에 매번 실행된다.
     * */
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    /**
     * 개별 테스트 이후에 매번 실행된다.
     * */
    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}