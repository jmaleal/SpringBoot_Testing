package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class DemoApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void itShouldAddNumbers_Junit() {
        //given 
        int n1 = 10;
        int n2 = 20;

        //When
        int result = underTest.add(n1, n2);

        //then
        assertEquals(result, 30);

    }

    @Test
    void itShouldAddNumbers_AssertJ() {
        //given 
        int n1 = 10;
        int n2 = 20;

        //When
        int result = underTest.add(n1, n2);

        //then
        assertThat(result).isEqualTo(30);

    }

    class Calculator {

        int add(int a, int b) {
            return a + b;
        }
    }

}
