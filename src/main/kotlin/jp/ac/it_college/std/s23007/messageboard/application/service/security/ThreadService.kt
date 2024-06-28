package jp.ac.it_college.std.s23007.messageboard.application.service


import jp.ac.it_college.std.s23007.messageboard.domain.model.Threads
import jp.ac.it_college.std.s23007.messageboard.domain.repository.ThreadRepository
import kotlinx.datetime.LocalDateTime
import org.springframework.stereotype.Service


@Service
class ThreadService(
    private val threadRepository: ThreadRepository,
) {
    fun createThread(
        title: String,
        userId: Long,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime,
        deleted: Boolean
    ): Threads {
        val newThread = Threads(
            id = 0,
            title = title,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deleted = deleted,
        )
        return threadRepository.createThread(newThread)
    }

//    fun getThreadById(id: Long): Threads? {
//        return threadRepository.getThreadById(id)
//
//    }

    fun getAllThreads(): List<Threads> {
        return threadRepository.getAllThreads()
    }

    fun updateThread(
        id: Long,
        title: String,
        userId: Long,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime,
        deleted: Boolean
    ): Threads {
        val existingThread = threadRepository.getThreadById(id)
            ?: throw IllegalArgumentException("Thread not found with id")
        val updatedThread = existingThread.copy(
            title = title,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deleted = deleted
        )
        return threadRepository.updateThread(updatedThread)
    }

    fun deleteThread(id: Long) {
        threadRepository.deleteThread(id)
    }

    fun getDetails(id: Long): Threads? {
        return threadRepository.getThreadById(id)

    }

}