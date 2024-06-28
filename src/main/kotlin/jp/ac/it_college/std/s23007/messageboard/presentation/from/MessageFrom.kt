package jp.ac.it_college.std.s23007.messageboard.presentation.from


import jp.ac.it_college.std.s23007.messageboard.domain.model.MessageWithThreads
import jp.ac.it_college.std.s23007.messageboard.domain.model.Messages
import kotlinx.datetime.LocalDateTime


@Serializable
data class GetMessageListResponse(
    val threadId: Long,
    val title: String,
    val messages: List<MessageInfo>
)

@Serializable
data class MessageInfo(
    val id: Long,
    val userId: Long?,
    val username: String?,
    val message: String?,
    val postedAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    constructor(model: Messages) : this(
        id = model.id,
        userId = if (model.messageDeleted) null else model.userId,
        username = if (model.messageDeleted) null else model.view_name,
        message = if (model.messageDeleted) null else model.message,
        postedAt = model.messageUpdateAt,
        updatedAt = model.messageUpdateAt,
    )
}

@Serializable
data class PostMessageRequest(
    val message: String
)

@Serializable
data class PostedMessageResponse(
    val id: Long,
    val threadId: Long,
    val userId: Long,
    val postedAt: LocalDateTime,
) {
    constructor(model: MessageWithThreads) : this(
        id = model.id,
        threadId = model.Thread.id,
        userId = model.user.id,
        postedAt = model.postedAt
    )
}

@Serializable
data class PutMessageUpdateRequest(
    val message: String,
)

@Serializable
data class MessageUpdateResponse(
    val id: Long,
    val threadId: Long,
    val message: String,
    val updatedAt: LocalDateTime,
)

@Serializable
data class MessageDeleteResponse(
    val id: Long,
    val threadId: Long,
    val updatedAt: LocalDateTime,
)