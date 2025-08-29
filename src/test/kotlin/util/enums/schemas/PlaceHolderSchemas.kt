package util.enums.schemas

enum class PlaceHolderSchemas(val schema: String) {
    GET_POSTS("schema/get_posts.json"),
    GET_ALL_POSTS("schema/get_all_posts.json"),
    GET_POST_COMMENTS_BY_ID("schema/get_post_comments_by_id.json"),
    GET_COMMENTS_BY_POST_ID("schema/get_comments_by_post_id.json"),
    POST_CREATE_POST("schema/create_post.json"),
}