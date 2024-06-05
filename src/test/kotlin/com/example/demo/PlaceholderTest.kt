package com.example.demo

import io.restassured.RestAssured
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import util.enums.schemas.PlaceHolderSchemas

@SpringBootTest
class PlaceholderTest {

    @Test
    fun `Get posts by id`(){
        val response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1")

        response
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(PlaceHolderSchemas.GET_POSTS.schema))

        println(response.headers.asList())
        println(response.body.asString())
    }

    @Test
    fun `Get all posts`(){
        val response = RestAssured.get("https://jsonplaceholder.typicode.com/posts")

        response
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(PlaceHolderSchemas.GET_ALL_POSTS.schema))

        println(response.headers.asList())
        println("Response body: " + response.body.asString())
    }
}