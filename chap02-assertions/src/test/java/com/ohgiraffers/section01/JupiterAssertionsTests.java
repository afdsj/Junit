package com.ohgiraffers.section01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class JupiterAssertionsTests {

    @Test
    @DisplayName("더하기 기능 테스트")
    void testAssertEquals(){

        //given : 검증을 위한 파라미터를 준비하는 단계
        int first = 10;
        int second = 20;
        int expected = first + second;

        //when  : 테스트를 진행할 메서드 호출
        Calculator calculator = new Calculator();
        int result = calculator.plusTwoNumbers(first, second);

        //then  : 실행 결과를 검증

        // 일반 리터럴 값으로 표현
        Assertions.assertEquals(expected, result,"불일치 한 경우 보여지는 메시지");

        // 람다 표현식
        Assertions.assertEquals(expected, result, ()-> "불일치 한 경우 보여지는 메시지");
    }

    @Test
    @DisplayName("인스턴스 동일성 비교 테스트")
    void testAssertNotEqualsWithInstances(){

        // given
        Object obj1 = new Object();

        // when
        Object obj2 = new Object();

        // then
        // hashcode, equals
        Assertions.assertNotEquals(obj1, obj2);
    }

    @Test
    @DisplayName("null인지 테스트")
    void testAssertNull(){

        // given
        String str;

        // when
        str = null;

        // then
        Assertions.assertNull(str);
    }

    @Test
    @DisplayName("null이 아닌지 테스트")
    public void notNullMethod(){

        // given
        String str;

        // when
        str = "java";

        // then
        Assertions.assertNotNull(str);
    }

    @Test
    @DisplayName("두 값이 같은지 확인")
    void testAssertTrue(){

        // given
        int num1 = 10;
        int num2 = 10;

        // when
        boolean result = num1 == num2;

        //then
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("두 값이 다른지 확인")
    void testAssertFalse(){

        // given
        int num1 = 10;
        int num2 = 20;

        // when
        boolean result = num1 == num2;

        // then
//        Assertions.assertNotEquals(num1, num2);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("동시에 여러 가지 값에 대한 검증을 수행하는 경우 테스트")
    void testAssertAll(){

        // given
        String firstName = "길동";
        String lastName = "홍";

        // when
        Person person = new Person(firstName, lastName);

        // then
        Assertions.assertAll("그룹화 된 테스트의 이름(실패시 보여짐)",
                () -> Assertions.assertEquals(firstName, person.getFirstName(), ()-> "firstName이 잘못됨"),
                () -> Assertions.assertEquals(lastName, person.getLastName(), ()->"lastName이 잘못됨")
                );
    }

    @Test
    @DisplayName("인스턴스 타입의 대한 검증을 수행하는 경우")
    void testAssertType(){

        // given
        String firstName = "길동";
        String lastName = "홍";

        // when
        Person person = new Person(firstName, lastName);

        // then
//        Assertions.assertInstanceOf(Person.class, person);

        // 람다 표현식
        Assertions.assertAll(
                ()-> Assertions.assertInstanceOf(Person.class, person),
                ()-> Assertions.assertInstanceOf(Object.class, person)
        );
    }

    @Test
    @DisplayName("void 메소드의 경우 exception 발생 없이 정상적으로 동작하는지 테스트")
    void testAssertDoesNotThrow(){

        // given
        int firstNum = 10;
        int secondNum = 3;

        // when & then
        PositiveNumberValidator positiveNumberValidator = new PositiveNumberValidator();
        Assertions.assertDoesNotThrow(
                ()-> positiveNumberValidator.checkDividableNumbers(firstNum, secondNum)
        );
    }

    @Test
    @DisplayName("void 메소드의 경우 exception이 발생하는지 테스트")
    void testAssertThrows(){

        // given
        int firstNum = 10;
        int secondNum = 0;
        String errorMessage = "0으로 나눌 수 없습니다";

        // when & then
//        PositiveNumberValidator validator = new PositiveNumberValidator();
//        Assertions.assertThrows(
//            IllegalArgumentException.class,
//                ()-> validator.checkDividableNumbers(firstNum, secondNum)
//        );

        PositiveNumberValidator validator = new PositiveNumberValidator();
        Exception exception = Assertions.assertThrows(
                Exception.class,
                ()-> validator.checkDividableNumbers(firstNum,secondNum)
                );

        Assertions.assertAll(
                ()-> Assertions.assertInstanceOf(IllegalArgumentException.class, exception,()->"예외 타입이 일치하지 않음"),
                ()-> Assertions.assertEquals(errorMessage, exception.getMessage(), ()->"에러 메시지가 같지 않습니다")
        );
    }

    @Test
    @DisplayName("예상 목록이 실제 목록과 일치하는지 확인")
    void testAssertLinesMatch(){

        // given
        List<String> expected = Arrays.asList("java","database","spring");

        // when
        List<String> actual = Arrays.asList("java","database","spring");

        // then
        Assertions.assertLinesMatch(expected, actual, ()-> "두 리스트의 값이 같지 않음");
    }

}
