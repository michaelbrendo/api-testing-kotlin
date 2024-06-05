package services

import io.restassured.RestAssured
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import io.restassured.module.jsv.JsonSchemaValidator.reset
import io.restassured.response.ResponseBody
import util.Data
import util.enums.schemas.PlaceHolderSchemas

class PlaceholderService {

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