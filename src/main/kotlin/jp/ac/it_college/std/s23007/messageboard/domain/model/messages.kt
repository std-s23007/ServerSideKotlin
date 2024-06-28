package jp.ac.it_college.std.s23007.messageboard.domain.model

import kotlinx.datetime.LocalDateTime


data class Messages(
    val id: Long,
    val threadId: Long,
    val userId: Long,
    var message: String,
    val postedAt: LocalDateTime,
    val messageUpdateAt: LocalDateTime,
    var messageDeleted: Boolean,
    val view_name: String
)