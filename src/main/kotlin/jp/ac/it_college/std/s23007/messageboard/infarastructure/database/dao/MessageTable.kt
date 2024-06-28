package jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MessageTable : LongIdTable("messages") {
    val threadId = reference("thread_id", ThreadsTable)
    val userId = reference("user_id", UserTable)
    val message = varchar("message", 256)
    val postedAt = datetime("posted_at")
    val updatedAt = datetime("updated_at")
    val deleted = bool("deleted").default(false)
}