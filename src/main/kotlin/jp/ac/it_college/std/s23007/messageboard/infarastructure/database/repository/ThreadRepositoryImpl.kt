package jp.ac.it_college.std.s23007.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23007.messageboard.domain.model.Threads
import jp.ac.it_college.std.s23007.messageboard.domain.repository.ThreadRepository
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.ThreadsEntity
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository


@Repository
class ThreadRepositoryImpl : ThreadRepository {
    override fun createThread(thread:Threads):Threads {
        return transaction {
            val newThread = ThreadsEntity.new {
                title = thread.title
                userId = UserEntity[thread.userId]
                createdAt = thread.createdAt
                updatedAt = thread.updatedAt
                deleted = thread.deleted
            }
            newThread.toThread()
        }
    }

    override fun getThreadById(id: Long): Threads? {
        return transaction {
            val threadEntity = ThreadsEntity.findById(id)
            threadEntity?.toThread()
        }
    }

    override fun getAllThreads(): List<Threads> {
        return transaction {
            ThreadsEntity.all().map { it.toThread() }
        }
    }

    override fun updateThread(thread: Threads): Threads {
        return transaction {
            val threadEntity = ThreadsEntity.findById(thread.id)
                ?: throw IllegalArgumentException("threads not found with id")

            threadEntity.apply {
                title = thread.title
                userId = UserEntity[thread.userId]
                createdAt = thread.createdAt
                updatedAt = thread.updatedAt
                deleted = thread.deleted
            }
            threadEntity.toThread()
        }
    }

    override fun deleteThread(id: Long) {
        transaction {
            ThreadsEntity.findById(id)?.delete()
        }
    }

}