package section05.custom;

public class CustomAnnotationTests {

    @WindowsTest
    void windCustom(){
        System.out.println("테스트");
    }

    @DevelopmentTest
    void devTest(){
        System.out.println("dev 테스트 좀 할게요");
    }

    @ProductionTest
    void proTest(){
        System.out.println("Production 테스트");
    }

    @MacTest
    void macCustom(){
        System.out.println("맥에서만 테스트 할게요");
    }


}
