package util
import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

object Helper {

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return  email.matches(emailRegex)
    }

    fun validateComments(comments: List<Map<String, Any>>, expectedPostId: Int) {
        comments.forEach { comment ->
            assertEquals(expectedPostId, comment["postId"], "postId should match expected")
            assertTrue(comment["id"] as Int > 0, "id should be positive")
            assertTrue((comment["name"] as String).isNotBlank(), "Name should not be blank")

            val email = comment["email"] as String
            assertTrue(email.isNotBlank(), "Email should not be blank")
            assertTrue(isValidEmail(email), "Email should be valid")
            assertTrue((comment["body"] as String).isNotBlank(), "Body should not be blank")
        }
    }

    fun allureLogger(url: String, method: String, requestPayload: Any?, response: Response) {

        Allure.step("Request: $url/$method", ThrowableRunnableVoid {
//            requestHeaders :TODO
            requestPayload?.let { Allure.addAttachment("Payload", it.toString()) }
        })

        Allure.step("Response:", ThrowableRunnableVoid {
            Allure.addAttachment("Status code:", response.statusCode.toString())
            Allure.addAttachment("Response Headers:", response.headers.toString())
            Allure.addAttachment("Response Body:", response.body.asString())
        })
    }
}