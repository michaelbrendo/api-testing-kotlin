package tests

import io.restassured.internal.RestAssuredResponseImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import payloads.PostPayload
import services.PlaceholderService
import util.enums.schemas.PlaceHolderSchemas


@SpringBootTest
class PlaceholderTest {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlaceholderTest::class.java)
    }

    fun logResponse(response: RestAssuredResponseImpl) {
        logger.info("Headers: " + response.headers.asList())
        logger.info("Body: " + response.body.asString())
    }

    @Test
    fun `Should create post`(){
        val payload = PostPayload("foo", "bar", 1)
        val response = PlaceholderService().createPost(payload, PlaceHolderSchemas.POST_CREATE_POST.schema) as RestAssuredResponseImpl

        val postResponse: PostPayload = response.`as`(PostPayload::class.java)

        assertEquals(payload.title, postResponse.title)
        assertEquals(payload.body, postResponse.body)
        assertEquals(payload.userId, postResponse.userId)

        logResponse(response)
    }

    @Test
    fun `Get posts by id`() {
        val response = PlaceholderService().getPostsById(PlaceHolderSchemas.GET_POSTS.schema) as RestAssuredResponseImpl

        logResponse(response)
    }

    @Test
    fun `Get all posts`() {
        val response = PlaceholderService().getAllPosts(PlaceHolderSchemas.GET_ALL_POSTS.schema) as RestAssuredResponseImpl

        logResponse(response)
    }

    @Test
    fun `Get post comment by id`() {
        val response = PlaceholderService().getPostCommentById(PlaceHolderSchemas.GET_POST_COMMENTS_BY_ID.schema) as RestAssuredResponseImpl

        logResponse(response)
    }

    @Test
    fun `Get comment by postId`() {
        val response = PlaceholderService().getCommentByPostId(PlaceHolderSchemas.GET_COMMENTS_BY_POST_ID.schema) as RestAssuredResponseImpl

        logResponse(response)
    }
}