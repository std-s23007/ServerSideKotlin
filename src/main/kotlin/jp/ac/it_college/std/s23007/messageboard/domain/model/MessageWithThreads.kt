package jp.ac.it_college.std.s23007.messageboard.domain.model


import kotlinx.datetime.LocalDateTime



data class MessageWithThreads(
    val id: Long,
    val Thread: Threads,
    val user: Users,
    val message: String,
    val postedAt: LocalDateTime,
    val messageUpdateAt: LocalDateTime,
    val messageDeleted: Boolean
)