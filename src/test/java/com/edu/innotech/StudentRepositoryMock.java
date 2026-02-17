package com.edu.innotech;

public class StudentRepositoryMock implements StudentRepository {
    public int getRatingForGradeSum(int sum){
        return 5;
    }

    public long count() {
        return 0;
    }

    public void delete(Student entity) {

    }

    public void deleteAll(Iterable<Student> entities) {

    }

    public Iterable<Student> findAll() {
        return null;
    }

    public Student save(Student entity) {
        return null;
    }

    public Iterable<Student> saveAll(Iterable<Student> entities) {
        return null;
    }

}
