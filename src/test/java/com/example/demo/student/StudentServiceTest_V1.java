/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 *
 * @author JORGE_PC
 */
public class StudentServiceTest_V1 {

    @Mock
    /*usamos mock e não @Autowired porque o Repository sabemos que a implementação está correta e cada um doz métodos funciona.
    POr isso usamos Mock. Além disso devemos ser capazes de testar o service sem necessidade de recorrer a uma real persistência do repository*/
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach //antes de cada teste vai instanciar um novo Service
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this); //vai iniciar todos os objetos que estiverem com a anotação @Mock
        underTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();//vai fechar os mocks abertos em cada teste

    }

    @Test
   // @Disabled // pode evitar o test enquanto tiver esta anotaçao
    public void testAllStudents() {
        //nao faz sentido fazer este teste porque ele retorna uma funcao nativa do Repository, que está implementada e testada na framework
        //when
        underTest.getAllStudents();

        //then
        verify(studentRepository).findAll(); //vamos verificar se o métido findAll foi incovado. nao estamos a verificar o retorno, mas sim a invocação.

        //
    }

    @Test
    public void testAddStudent() {
    }

    @Test
    public void testDeleteStudent() {
    }

}
