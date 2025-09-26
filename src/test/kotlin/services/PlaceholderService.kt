package services

import io.restassured.RestAssured
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import io.restassured.response.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import payloads.PostPayload
import tests.PlaceholderTest
import util.Data
import util.Helper

class PlaceholderService {

    private val baseUrl = Data.baseUrl

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlaceholderTest::class.java)
    }

    private fun logResponse(response: Response) {
        logger.info("Headers: ${response.headers.asList()}")
        logger.info("Body: ${response.body.asString()}")
        logger.info("Schema validation successful")
    }

    fun createPost(payload: PostPayload, schema: String): Response {

        val response = RestAssured
            .given()
            .baseUri(baseUrl)
            .contentType("application/json")
            .body(payload)
            .`when`()
            .post("/posts")
            .then()
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()

        Helper.allureLogger(baseUrl, "post", payload, response)
        logResponse(response)
        return response
    }

    fun getPostsById(postId: Int, schema: String): Response {

        val response = RestAssured
            .given()
            .baseUri(baseUrl)
            .`when`()
            .get("/posts/$postId")
            .then()
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()

        Helper.allureLogger(baseUrl, "post", null, response)
        logResponse(response)
        return response
    }

    fun getAllPosts(schema: String): Response {

        val response = RestAssured
            .given()
            .baseUri(baseUrl)
            .`when`()
            .get("/posts")
            .then()
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()

        Helper.allureLogger(baseUrl, "post", null, response)
        logResponse(response)
        return response
    }

    fun getPostCommentById(commentId: Int, schema: String): Response {

        val response = RestAssured
            .given()
            .baseUri(baseUrl)
            .`when`()
            .get("posts/$commentId/comments")
            .then()
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()

        Helper.allureLogger(baseUrl, "post", null, response)
        logResponse(response)
        return response
    }

    fun getCommentByPostId(postId: Int, schema: String): Response {

        val response = RestAssured
            .given()
            .baseUri(baseUrl)
            .`when`()
            .get("comments?postId=$postId")
            .then()
            .body(matchesJsonSchemaInClasspath(schema))
            .extract().response()

        Helper.allureLogger(baseUrl, "post", null, response)
        logResponse(response)
        return response
    }
}