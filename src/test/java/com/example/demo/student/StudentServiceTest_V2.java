/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author JORGE_PC
 */
@ExtendWith(MockitoExtension.class)
/*ao colocar a anotação acima, não preciso de (ao contrario do test_V1:

i) no @BeforeEach deixo apenas a inicialização do underTest
ii) usar o @AfterEach
ii) não preciso do Autocloseable

ele vai criar os Mocks todos que tiver a anotação @Mock
 */
public class StudentServiceTest_V2 {

    @Mock
    /*usamos mock e não @Autowired porque o Repository sabemos que a implementação está correta e cada um doz métodos funciona.
    POr isso usamos Mock. Além disso devemos ser capazes de testar o service sem necessidade de recorrer a uma real persistência do repository*/
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach //antes de cada teste vai instanciar um novo Service
    void setUp() {
        underTest = new StudentService(studentRepository);
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
        //give
        String email = "jamila@jamila.com";
        Student st = new Student("jamila", email, Gender.FEMALE);

        //when
        underTest.addStudent(st);

        //then
        verify(studentRepository).save(st);

        //mas se queremos até verificar se o argumento do save é de fato o student que criamos, fazemos
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent.equals(st));
    }

    @Test
    public void willThroWhenEmailIsTaken() {
        //give
        String email = "jamila@jamila.com";
        Student st = new Student("jamila", email, Gender.FEMALE);

        given(studentRepository.selectExistsEmail(st.getEmail())).willReturn(Boolean.TRUE);
        //esta linha acima obriga o métido selectExistsEmail a retornar TRUE. de outra forma nunca iria fazer o throw exception
        //este given vem do mockito

        //then
        assertThatThrownBy(() -> underTest.addStudent(st))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + st.getEmail() + " taken");

        //se faz um throw exception, entao o método save do metodo nunca será chamado
        verify(studentRepository, never()).save(any());
    }

    @Test
    public void testDeleteStudent() {
    }

}
