package tests

import io.restassured.internal.RestAssuredResponseImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import payloads.PostPayload
import services.PlaceholderService
import util.enums.schemas.PlaceHolderSchemas


@SpringBootTest
class PlaceholderTest {

    @Test
    fun `Should create post`() {
        val payload = PostPayload("foo", "bar", 1)
        val response = PlaceholderService().createPost(payload, PlaceHolderSchemas.POST_CREATE_POST.schema) as RestAssuredResponseImpl
        val postResponse: PostPayload = response.`as`(PostPayload::class.java)

        assertEquals(CREATED.value(), response.statusCode)
        assertEquals(payload.title, postResponse.title)
        assertEquals(payload.body, postResponse.body)
        assertEquals(payload.userId, postResponse.userId)
    }

    @Test
    fun `Should get posts by id`() {
        val response = PlaceholderService().getPostsById(PlaceHolderSchemas.GET_POSTS.schema) as RestAssuredResponseImpl
        val json = response.jsonPath()

        assertEquals(OK.value(), response.statusCode)
        assertEquals(1, json.getInt("userId"))
        assertEquals(1, json.getInt("id"))
        assertTrue(json.getString("title").isNotBlank(), "Title should not be blank")
        assertTrue(json.getString("body").isNotBlank(), "Body should not be blank")
    }

    @Test
    fun `Should get all posts`() {
        val response = PlaceholderService().getAllPosts(PlaceHolderSchemas.GET_ALL_POSTS.schema) as RestAssuredResponseImpl

        assertEquals(OK.value(), response.statusCode)
    }

    @Test
    fun `should get post comment by id`() {
        val response = PlaceholderService().getPostCommentById(PlaceHolderSchemas.GET_POST_COMMENTS_BY_ID.schema) as RestAssuredResponseImpl

        assertEquals(OK.value(), response.statusCode)
    }

    @Test
    fun `Should get comment by postId`() {
        val response = PlaceholderService().getCommentByPostId(PlaceHolderSchemas.GET_COMMENTS_BY_POST_ID.schema) as RestAssuredResponseImpl

        assertEquals(OK.value(), response.statusCode)
    }
}