/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author JORGE_PC
 */
@DataJpaTest //vai buscar a BD da H2 em aplication.properties em Other Test Sources. Tenho de colocar isto sempre que estou a testar repositorios uma vez que têm de conectar com BD
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    public void testSelectExistsEmail() {
        //give
        String email = "jamila@jamila.com";
        Student st = new Student("jamila", email, Gender.FEMALE);
        underTest.save(st);

        //when
        boolean achou = underTest.selectExistsEmail(st.getEmail());

        //then
        assertThat(achou).isTrue();

    }

}
