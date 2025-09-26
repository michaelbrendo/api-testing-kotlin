package tests

import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Story
import io.qameta.allure.junit4.Tag
import io.restassured.internal.RestAssuredResponseImpl
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import payloads.PostPayload
import services.PlaceholderService
import util.Helper
import util.enums.schemas.PlaceHolderSchemas

@SpringBootTest(classes = [Any::class])
class PlaceholderTest {

    @Test
    @Tag("smoke")
    @Epic("Place holder API")
    @Feature("Post and Get features")
    @Story("Create Post")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test
    @Tag("smoke")
    @Epic("Place holder API")
    @Feature("Post and Get features")
    @Story("Create GET")
    @Severity(SeverityLevel.CRITICAL)
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
    @Tag("smoke")
    @Epic("Place holder API")
    @Feature("Post and Get features")
    @Story("Create GET")
    @Severity(SeverityLevel.NORMAL)
    fun `Should get all posts`() {
        val response = PlaceholderService().getAllPosts(PlaceHolderSchemas.GET_ALL_POSTS.schema) as RestAssuredResponseImpl

        assertEquals(OK.value(), response.statusCode)
        assertTrue(response.jsonPath().getList<Any>("").isNotEmpty(), "Posts list should not be empty")
    }

    @Test
    @Tag("smoke")
    @Epic("Place holder API")
    @Feature("Post and Get features")
    @Story("Create Comments")
    @Severity(SeverityLevel.CRITICAL)
    fun `should get post comment by id`() {
        val response = PlaceholderService().getPostCommentById(1, PlaceHolderSchemas.GET_POST_COMMENTS_BY_ID.schema) as RestAssuredResponseImpl
        val firstComment = response.jsonPath().getList<Map<String, Any>>("$")

        assertEquals(OK.value(), response.statusCode)
        Helper.validateComments(firstComment, 1)
    }

    @Test
    @Tag("smoke")
    @Epic("Place holder API")
    @Feature("Post and Get features")
    @Story("Create Comments")
    @Severity(SeverityLevel.NORMAL)
    fun `Should get comment by postId`() {
        val response = PlaceholderService().getCommentByPostId(1, PlaceHolderSchemas.GET_COMMENTS_BY_POST_ID.schema) as RestAssuredResponseImpl
        val firstComment = response.jsonPath().getList<Map<String, Any>>("$")

        assertEquals(OK.value(), response.statusCode)
        Helper.validateComments(firstComment, 1)
    }

    @Severity(SeverityLevel.MINOR)
    @Tag("smoke")
    @Test
    fun `should fail`() {
//        assertTrue(false)
    }

}