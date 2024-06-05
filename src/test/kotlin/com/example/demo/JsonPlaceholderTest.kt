package com.example.demo

import io.restassured.RestAssured
import io.restassured.internal.common.assertion.Assertion
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.nio.file.Paths

@SpringBootTest
class JsonPlaceholderTest {
    val GET_POSTS = "schema/get_posts.json"
    @Test
    fun `Test get posts`(){
        val response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1")

        response
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(GET_POSTS))

        println(response.headers.asList())
        println(response.body.asString())
    }
}