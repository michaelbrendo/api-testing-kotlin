package services

import io.restassured.RestAssured
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import io.restassured.response.ResponseBody
import payloads.PostPayload
import util.Data

class PlaceholderService {

    fun createPost(payload: PostPayload, schema: String): ResponseBody<*>? {

        return RestAssured
            .given()
            .baseUri(Data.baseUrl)
            .contentType("application/json")
            .body(payload)
            .`when`()
            .post("/posts")
            .then()
            .statusCode(201)
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()
    }

    fun getPostsById(schema: String): ResponseBody<*>?{

        return RestAssured
            .given()
            .baseUri(Data.baseUrl)
            .`when`()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()
    }

    fun getAllPosts(schema: String): ResponseBody<*>?{

        return  RestAssured
            .given()
            .baseUri(Data.baseUrl)
            .`when`()
            .get("/posts")
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()
    }

    fun getPostCommentById(schema: String): ResponseBody<*>?{

        return RestAssured
            .given()
            .baseUri(Data.baseUrl)
            .`when`()
            .get("posts/1/comments")
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()
    }

    fun getCommentByPostId(schema: String): ResponseBody<*>? {

        return  RestAssured
            .given()
            .baseUri(Data.baseUrl)
            .`when`()
            .get("comments?postId=1")
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()
    }
}