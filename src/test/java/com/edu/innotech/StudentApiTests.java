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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentApiTests {
    static private int uniqueId;

    @BeforeAll
    public static void setUp() {
        Random random = new Random();
        uniqueId = random.nextInt(99999) + 1;
    }
    @Test
    @Order(1)
    public void createNewStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("{\"id\": " + uniqueId + ",\"name\": \"John\", \"marks\": [4]}")
                .when()
                .post()
                .then()
                .defaultParser(Parser.JSON)
                .statusCode(201);
    }

    @Test
    @Order(2)
    public void updateExistStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("{\"id\": " + uniqueId + ",\"name\": \"John\", \"marks\": [5]}")
                .when()
                .post()
                .then()
                .defaultParser(Parser.JSON)
                .statusCode(201);
    }

    @Test
    @Order(3)
    public void getExistStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student/"+ uniqueId)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(uniqueId))
                .body("name", Matchers.equalTo("John"))
                .body("marks[0]", Matchers.equalTo(5));
    }
    @Test
    public void getNonExistStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student/-999999")
                .when()
                .get()
                .then()
                .statusCode(404);
    }
    @Test
    public void createStudentWithoutId(){
                RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Ivan\", \"marks\": [4]}")
                .when()
                .post()
                .then()
                .defaultParser(Parser.JSON)
                .statusCode(201)
                .extract().as(Integer.class);
    }
    @Test
    public void createStudentWithoutName(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("{\"marks\": [4]}")
                .when()
                .post()
                .then()
                .defaultParser(Parser.JSON)
                .statusCode(400);
    }

    @Test
    @Order(4)
    public void deleteStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student/" + uniqueId)
                .when()
                .delete()
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteNonExistStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student/-1")
                .when()
                .delete()
                .then()
                .statusCode(404);
    }

    @Test
    public void getTopStudents(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("{\"id\": 777777,\"name\": \"John\", \"marks\": [5, 5, 5, 5, 5, 5]}")
                .when()
                .post()
                .then()
                .defaultParser(Parser.JSON)
                .statusCode(201);
        RestAssured.given()
                .baseUri("http://localhost:8080/topStudent")
                .when()
                .get()
                .then()
                .statusCode(200);
    }
}
