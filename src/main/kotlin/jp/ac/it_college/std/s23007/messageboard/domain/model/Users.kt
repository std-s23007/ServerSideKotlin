package jp.ac.it_college.std.s23007.messageboard.domain.model

data class Users(
    val id: Long,
    val viewName: String,
    val email: String,
    val password: String
)