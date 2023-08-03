package section01.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

//@DisplayName("테스트 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestAnnotationTests {

    /* 필기
     *  테스트 클래스는 적어도 한 개 이상의 @Test 어노테이션이 달린 메소드를 가진 클래스이다
     *  테스트 클래스는 abstract이면 안되고, 한 개의 생성자를 가지고 있어야 한다
     *  (두 개 이상 작성하면 런타임시 PreconditionViolationException이 발생한다)
     *  아무런 생성자도 작성하지 않으면 기본 생성자는 컴파일러가 자동으로 추가한다*/

    public TestAnnotationTests(){}
//    public TestAnnotationTests(int a){}

    /* @Test 메소드는 단독으로 실행이 가능하며 테스트 메소드를 만들기 위해서는 @Test 어노테이션을 붙여줘야 한다*/
    @Test
    /* 기본적으로 메소드메서드의 이름을 따라가지만 @DisplayName()을 사용하여 이름을 설정하는 것이 가능하다*/
    @DisplayName("이단비 지각함")
    public void 테스트(){}

    @Test
    /* 과거에는 공백문자를 언더바로 표기한 한글 메소드를 작성하였다*/
    public void 테스트_체스트(){
        /* 클래스 레벨에 @DisplayNameGenerator를 붙이고 DisplayNameGenerator.ReplaceUnderscores.class 설정을 해주면
        *  메소드의 _를 공백으로 치환하여 처리한다 이때 @DisplayName과 중복으로 설정되는 경우 @DisplayName이 우선권을 갖는다*/
    }

//    @Test // 에러 발생
//    private void 될까요(){
        /* 과거 junit 1.1버전에서 리플랙션의 기능을 이용하기 위해 public 설정만 허용을 하였으나
         *  junit4 버전부터는 public이 아닌 다른 접근제어자도 허용하게 되었다 (private 제외)
         *  그러나 과거 전통을 유지하기 위해서 public으로 작성을 하였으며
         *  junit5 이후부터 이러한 전통을 풀어버렸다 그러나 private 접근제어자는 사용이 불가능하다
         * */
//    }

    @Test
    void junit4_부터는_default_접근제어자도_허용(){}

    /* junit은 순서를 보장하지 않는다*/
}
