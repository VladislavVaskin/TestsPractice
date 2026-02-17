package com.edu.innotech;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.api.Assertions.*;

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
        assertEquals(lst, student1.getGrades(), "Оценки не совпадают с ожидаемыми значениями");
    }

    @ParameterizedTest(name = "добавление некорректных оценок")
    @MethodSource("com.edu.innotech.MarksGenerator#ints")
    public void marksNotInRange(int x) {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> student1.addGrade(x), "Ожидается исключение IllegalArgumentException для некорректной оценки");
    }

    @Test
    public void testEquals() {
        assertEquals(student1, student2, "Студенты не равны, хотя должны быть равны");
    }

    @Test
    public void testNotEquals() {
        assertNotEquals(student1, student3, "Студенты равны, хотя не должны быть равны");
    }

    @Test
    public void testHashCode() {
        assertEquals(student1.hashCode(), student2.hashCode(), "Хэш-коды студентов не равны, хотя должны быть равны");
    }

    @Test
    public void compareHashForDifferentObjects() {
        assertNotEquals(student1.hashCode(), student3.hashCode(), "Хэш-коды студентов равны, хотя не должны быть равны");
    }

    @Test
    public void testToStringNotNull() {
        assertNotNull(student1.toString(), "Метод toString() вернул null");
    }

    @Test
    public void testToStringContent() {
        student1.addGrade(2);
        student1.addGrade(3);
        student1.addGrade(4);

        assertTrue(student1.toString().contains("Peter"), "Метод toString() не содержит имя студента");
        assertTrue(student1.toString().contains("2"), "Метод toString() не содержит оценку 2");
        assertTrue(student1.toString().contains("3"), "Метод toString() не содержит оценку 3");
        assertTrue(student1.toString().contains("4"), "Метод toString() не содержит оценку 4");
    }
}

