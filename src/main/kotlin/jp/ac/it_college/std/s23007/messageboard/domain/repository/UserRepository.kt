package jp.ac.it_college.std.s23007.messageboard.domain.repository

import jp.ac.it_college.std.s23007.messageboard.domain.model.Users


interface UserRepository {
    fun findByUsername(username: String): Users?
    fun findByEmail(email: String): Users?
    fun findById(id: Long): Users?
}