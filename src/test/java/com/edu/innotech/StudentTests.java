package com.edu.innotech;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import javax.swing.text.Style;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTests {
    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setUp() {
        student1 = new Student("Peter");
        student2 = new Student("Peter");
        student3 = new Student("Ivan");
    }

    @Test
    public void marksInRange() {
        List<Integer> lst = List.of(2, 3, 4, 5);
        student1.addGrade(lst.get(0));
        student1.addGrade(lst.get(1));
        student1.addGrade(lst.get(2));
        student1.addGrade(lst.get(3));
        assertEquals(lst, student1.getGrades());
    }

    @ParameterizedTest(name = "добавление некорректных оценок")
    @MethodSource("com.edu.innotech.MarksGenerator#ints")
    public void marksNotInRange(int x) {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> student1.addGrade(x));
    }

    @Test
    public void testEquals() {
        assertEquals(student1, student2);
    }
    @Test
    public void testNotEquals() {
        assertNotEquals(student1, student3);
    }

    @Test
    public void testHashCode() {
        assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    public void compareHashForDifferentObjects(){
        assertNotEquals(student1.hashCode(), student3.hashCode());
    }

    @Test
    public void testToStringNotNull(){
        assertNotNull(student1.toString());
    }

    @Test
    public void testToStringContent(){
        student1.addGrade(2);
        student1.addGrade(3);
        student1.addGrade(4);

        assertTrue(student1.toString().contains("Peter"));
        assertTrue(student1.toString().contains("2"));
        assertTrue(student1.toString().contains("3"));
        assertTrue(student1.toString().contains("4"));
    }
}

