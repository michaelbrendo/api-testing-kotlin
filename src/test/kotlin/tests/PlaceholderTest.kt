package tests

import io.qameta.allure.*
import io.restassured.internal.RestAssuredResponseImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import payloads.PostPayload
import services.PlaceholderService
import util.Helper
import util.enums.schemas.PlaceHolderSchemas

@SpringBootTest(classes = [Any::class])
class PlaceholderTest {

    @Epic("Place holder API")
    @Feature("Post /posts")
    @Story("Should create posts")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("sanity")
    @Test
    fun `Should create post`() {
        val payload = PostPayload("foo", "bar", 1)
        val response = PlaceholderService().createPost(payload, PlaceHolderSchemas.POST_CREATE_POST.schema) as RestAssuredResponseImpl
        val postResponse: PostPayload = response.`as`(PostPayload::class.java)

        assertEquals(CREATED.value(), response.statusCode)
        assertEquals(payload.title, postResponse.title)
        assertEquals(payload.body, postResponse.body)
        assertEquals(payload.userId, postResponse.userId)
        assertEquals(101, response.jsonPath().getInt("id"))
    }

    @Epic("Place holder API")
    @Feature("GET /posts")
    @Story("Should get posts by id")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @Test
    fun `Should get posts by id`() {
        val response = PlaceholderService().getPostsById(1, PlaceHolderSchemas.GET_POSTS.schema) as RestAssuredResponseImpl
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
        assertTrue(response.jsonPath().getList<Any>("").isNotEmpty(), "Posts list should not be empty")
    }

    @Epic("Place holder API")
    @Feature("Comment /comments")
    @Story("Should get post comment by id")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("sanity")
    @Test
    fun `should get post comment by id`() {
        val response = PlaceholderService().getPostCommentById(1, PlaceHolderSchemas.GET_POST_COMMENTS_BY_ID.schema) as RestAssuredResponseImpl
        val firstComment = response.jsonPath().getList<Map<String, Any>>("$")
        val helper = Helper()

        assertEquals(OK.value(), response.statusCode)
        helper.validateComments(firstComment, 1)
    }

    @Epic("Place holder API")
    @Feature("Comment /comments?postId")
    @Story("Should get comment by postId")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("sanity")
    @Test
    fun `Should get comment by postId`() {
        val response = PlaceholderService().getCommentByPostId(1, PlaceHolderSchemas.GET_COMMENTS_BY_POST_ID.schema) as RestAssuredResponseImpl
        val firstComment = response.jsonPath().getList<Map<String, Any>>("$")
        val helper = Helper()

        assertEquals(OK.value(), response.statusCode)
        helper.validateComments(firstComment, 1)
    }
}