package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자A 10,000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: 사용자B 20,000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        // ThreadA: 사용자A는 10,000원을 기대했지만, 기대와 다르게 20,000원 출력
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}