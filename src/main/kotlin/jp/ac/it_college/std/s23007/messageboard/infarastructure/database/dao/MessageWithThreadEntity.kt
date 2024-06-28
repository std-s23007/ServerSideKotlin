package jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao

import jp.ac.it_college.std.s23007.messageboard.domain.model.MessageWithThreads
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MesssageWithThreadEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MesssageWithThreadEntity>(MessageWithThreads)

}