package com.example.demo

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JsonPlaceholderTest {

    @Test
    fun `Test get posts`(){
        val response: Response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1")

        println(response.headers.asList())
        println(response.body.asString())

        response
            .then()
            .statusCode(200)
    }
}