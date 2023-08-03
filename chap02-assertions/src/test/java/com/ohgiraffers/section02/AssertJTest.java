package com.ohgiraffers.section02;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class AssertJTest {

    @Test
    @DisplayName("문자열 테스트 하기") // 이름이 바껴서 나옴
    void testStringValidation(){

        String expected = "hello world";

        String actual = new String(expected);

        Assertions.assertThat(actual)
//                .isNotEmpty() //"", null 식별해줌 ( 공백이 있을 경우 값으로 인식)
                .isNotBlank() //"", null, " "
                .contains("hello") // "hello"를 포함하는가?
                .doesNotContain("hahahoho") // "hahahoho"를 포함하지 않는가?
                .startsWith("h") // "h"로 시작하는가?
                .endsWith("d") // "d"로 끝나는가?
                .isEqualTo("hello world"); // "hello world"랑 같은가?
    }

    @Test
    @DisplayName("숫자 테스트 하기")
    void testIntegerValidation(){
        double pi = Math.PI;

        Double actual = Double.valueOf(pi);

        Assertions.assertThat(actual)
                .isPositive()
                .isGreaterThan(3) // 3보다 크냐?
                .isLessThan(4) // 4보다 작냐?
                .isEqualTo(Math.PI);
    }

    @Test
    @DisplayName("날짜 테스트 하기")
    void testLocalDateTimeValidaiton(){
        String birthDay = "2014-09-18T16:42:00.000";

        LocalDateTime thatDay = LocalDateTime.parse(birthDay);

        Assertions.assertThat(thatDay)
                .hasYear(2014)
                .hasMonthValue(9)
                .hasDayOfMonth(18)
                .isBetween("2014-01-01T00:00:00.000", "2014-12-31T23:59:59.999")
                .isBefore(LocalDateTime.now());
    }

    @Test
    @DisplayName("예외 테스트 하기")
    void testExceptionValidaiton(){
        Throwable thorw = AssertionsForClassTypes.catchThrowable(
                ()->{
                    throw new IllegalArgumentException("잘못된 파라미터를 입력하였습니다");
                }
        );
        Assertions.assertThat(thorw)
                .isInstanceOf(IllegalArgumentException.class) // 매개변수로 넘겨진 클래스와 대상의 타입이 같은지
                .hasMessageContaining("파라미터"); // 매개변수를 메시지를 포함하고 있는지
    }

    @Test
    @DisplayName("예외 테스트 하기2")
    void testExceptionValidation2(){
//        주석 처리한 부분도 가능
//        Assertions.assertThatThrownBy(
//                ()->{
//                    throw new IllegalArgumentException("잘못된 파라미터를 입력하였습니다");
//                }).isInstanceOf(IllegalArgumentException.class) // 매개변수와 대상의 타입이 같은지
//                .hasMessageContaining("잘못된");

        /* 자주 사용하는 예외처리에 대한 정의된 함수를 제공한다
        *  1. assertThatNullPinterException
        *  2. assertThatIllegalArgumentException
        *  3. assertThatIllegalStateException
        *  4. assertThatIOException
        * */
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(()->{
                    throw new IllegalArgumentException("잘못된 파라미터를 입력하셨습니다");
                }).withMessageContaining("파라미터");
    }

//    @Test
//    void test(){
//        Throwable throwable = new IllegalArgumentException();
//
//        boolean result = (throwable instanceof IllegalArgumentException);
//
//        org.junit.jupiter.api.Assertions.assertTrue(result);
//    }

    @Test
    @DisplayName("예외를 던지지 않는 경우 테스트하기")
    void testNoneException(){
        Assertions.assertThatCode(
                ()->{
                    System.out.println("안녕 나는 예외가 없어");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("filltering assertion 테스트하기")
    void testFilterringAssertion(){

        Member member1 = new Member(1,"user01", "홍길동", 20);
        Member member2 = new Member(2,"user02", "유관순", 16);
        Member member3 = new Member(3,"user03", "이순신", 40);
        Member member4 = new Member(4,"user04", "임꺽정", 19);

        List<Member> members = Arrays.asList(member1,member2,member3,member4);

        Assertions.assertThat(members)
                .filteredOn(m -> m.getAge() > 20) // 변수 m이 20보다 큰거를 찾아줘
                .containsOnly(member3); // member3이 20보다 큰지?
    }

    @Test
    @DisplayName("객체의 Property 검증 테스트하기")
    void testPropertyValidation(){

        Member member1 = new Member(1,"user01", "홍길동", 20);
        Member member2 = new Member(2,"user02", "유관순", 16);
        Member member3 = new Member(3,"user03", "이순신", 40);
        Member member4 = new Member(4,"user04", "임꺽정", 19);

        List<Member> members = Arrays.asList(member1,member2,member3,member4);

        Assertions.assertThat(members)
                .filteredOn("age", 20) // 나이가 20인 사람을 찾기
                .containsOnly(member1); // member1가 맞아? (예상하기)
    }

    @Test
    @DisplayName("Property 추출 테스트하기")
    void testExtractProperty(){

        Member member1 = new Member(1,"user01", "홍길동", 20);
        Member member2 = new Member(2,"user02", "유관순", 16);
        Member member3 = new Member(3,"user03", "이순신", 40);
        Member member4 = new Member(4,"user04", "임꺽정", 19);

        List<Member> members = Arrays.asList(member1,member2,member3,member4);

        List<String> expected = Arrays.asList("홍길동","유관순","이순신","임꺽정");

        Assertions.assertThat(members)
                .extracting("name", String.class)
                .containsAll(expected);
    }

    @Test
    @DisplayName("튜플로 추출하여 테스트하기")
    void testExtractPropertyTuple(){

        Member member1 = new Member(1,"user01", "홍길동", 20);
        Member member2 = new Member(2,"user02", "유관순", 16);
        Member member3 = new Member(3,"user03", "이순신", 40);
        Member member4 = new Member(4,"user04", "임꺽정", 19);

        List<Member> members = Arrays.asList(member1,member2,member3,member4);

        Assertions.assertThat(members)
                .extracting("name","age")
                .contains(
                        Tuple.tuple("홍길동",20), // 그룹화
                        Tuple.tuple("유관순",16)
                );
    }

}
