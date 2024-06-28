package jp.ac.it_college.std.s23007.messageboard.application.service


import jp.ac.it_college.std.s23007.messageboard.domain.model.Messages
import jp.ac.it_college.std.s23007.messageboard.domain.repository.MessageRepository
import org.springframework.stereotype.Service




@Service
class MessageService(private val messageRepository: MessageRepository) {
    fun getListByThread(threadId: Long): List<Messages> {

        return messageRepository.getMessageByThreadId(threadId)
    }

    fun newPost(threadId: Long, message: String, userId: Long): Messages {

        val newMessage = Messages(
            id = 0L,
            threadId = threadId,
            userId = userId,
            postedAt = LocalDateTime.now(),
            message = messages
        )

        return messageRepository.createMessage(newMessage)
    }


    fun updateMessage(id: Long, messages: String, userId: Long): Messages {

        val existingMessage = messageRepository.getMessageById(id)
            ?: throw IllegalArgumentException("Message not found with id: $id")

        existingMessage.message = messages
        return existingMessage
    }

    fun deleteMessage(id: Long, userId: Long): Messages {

        val existingMessage = messageRepository.getMessageById(id)
            ?: throw IllegalArgumentException("Message not found with id: $id")

        // 論理削除を行う場合
        existingMessage.messageDeleted = true
        return existingMessage
    }
}