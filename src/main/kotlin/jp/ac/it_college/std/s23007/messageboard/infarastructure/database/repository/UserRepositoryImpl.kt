package jp.ac.it_college.std.s23007.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23007.messageboard.domain.model.Users
import jp.ac.it_college.std.s23007.messageboard.domain.repository.UserRepository
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.UserEntity
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.UserTable
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.UserTable.email
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findByUsername(username: String): Users? {
        return transaction {
            UserEntity.find {
                UserTable.email eq email
            }.singleOrNull()?.toUser()
        }
    }

    override fun findByEmail(email: String): Users? {
        return transaction {
            UserEntity.find {   UserTable.email eq email}
                .firstOrNull()?.toUser()
        }
    }

    override fun findById(id: Long): Users? {
        return transaction {
            UserEntity.findById(id)?.toUser()
        }

    }
}