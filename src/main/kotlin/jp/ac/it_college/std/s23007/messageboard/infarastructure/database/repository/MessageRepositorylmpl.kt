package jp.ac.it_college.std.s23007.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23007.messageboard.domain.model.Messages



import jp.ac.it_college.std.s23007.messageboard.domain.repository.MessageRepository
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.MessageEntity
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.MessageTable
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.ThreadsEntity
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.UserEntity

import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MessageRepositorylmpl: MessageRepository {
    override fun createMessage(message: Messages): Messages {
        return transaction {
            val newMessage = MessageEntity.new {
                threadId = ThreadsEntity[message.threadId]
                userId = UserEntity[message.userId]
                this.message = message.message
                postedAt = message.postedAt
                updatedAt = message.messageUpdateAt
                deleted = message.messageDeleted
            }
            newMessage.toMessage()
        }
    }

    override fun getMessageById(id: Long): Messages? {
        return transaction {
            val message = MessageEntity.findById(id)
            message?.toMessage()
        }
    }

    override fun getMessageByThreadId(threadId: Long): List<Messages> {
        return transaction {
            MessageEntity.find { MessageTable.threadId eq threadId }
                .map { it.toMessage() }
        }
    }

    override fun updateMessage(message: Messages): Messages {
        return transaction {
            val messageEntity = MessageEntity.findById(message.id)
                ?: throw IllegalArgumentException("Message not found with id: ${message.id}")

            messageEntity.apply {
                threadId = ThreadsEntity[message.threadId]
                userId = UserEntity[message.userId]
                this.message = message.message
                postedAt = message.postedAt
                updatedAt = message.messageUpdateAt
                deleted = message.messageDeleted
            }

            messageEntity.toMessage()
        }
    }

    override fun deleteMessage(id: Long) {
        transaction {
            MessageEntity.findById(id)?.delete()
        }
    }

}