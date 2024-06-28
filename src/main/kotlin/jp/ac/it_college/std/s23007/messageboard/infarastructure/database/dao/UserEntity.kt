package jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao


import jp.ac.it_college.std.s23007.messageboard.domain.model.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UserTable)

    var viewName by UserTable.viewName
    var email by UserTable.email
    var password by UserTable.password

    fun toUser(): Users {
        return Users(
            id.value,
            viewName,
            email,
            password
        )
    }
}