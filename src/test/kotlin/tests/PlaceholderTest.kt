package tests

import io.restassured.RestAssured
import io.restassured.internal.RestAssuredResponseImpl
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import services.PlaceholderService
import util.enums.schemas.PlaceHolderSchemas


@SpringBootTest
class PlaceholderTest {

    companion object{
        private val logger: Logger = LoggerFactory.getLogger(PlaceholderTest::class.java)
    }

    @Test
    fun `Get posts by id`(){
        val responseGetPostsById = PlaceholderService().getPostsById(PlaceHolderSchemas.GET_POSTS.schema) as RestAssuredResponseImpl

        logger.info("Headers: " + responseGetPostsById.headers.asList())
        logger.info("Body: " + responseGetPostsById.body.asString())
    }

    @Test
    fun `Get all posts`(){
        val responseGetAllPosts = PlaceholderService().getAllPosts(PlaceHolderSchemas.GET_ALL_POSTS.schema) as RestAssuredResponseImpl

        logger.info("Headers: " + responseGetAllPosts.headers.asList())
        logger.info("Body: " + responseGetAllPosts.body.asString())
    }

    @Test
    fun`Get post comment by id`(){
        val responseGetPostCommentById = PlaceholderService().getPostCommentById(PlaceHolderSchemas.GET_POST_COMMENTS_BY_ID.schema) as RestAssuredResponseImpl

        logger.info("Headers: " + responseGetPostCommentById.headers.asList())
        logger.info("Body: " + responseGetPostCommentById.body.asString())
    }

    @Test
    fun`Get comment by postId`(){
        val responseGetCommentByPostId = PlaceholderService().getCommentByPostId(PlaceHolderSchemas.GET_COMMENTS_BY_POST_ID.schema) as RestAssuredResponseImpl

        logger.info("Headers: " + responseGetCommentByPostId.headers.asList())
        logger.info("Body: " + responseGetCommentByPostId.body.asString())
    }
}