package jp.ac.it_college.std.s23007.messageboard.presentation.contoroller

import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.ThreadsTable.title
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.ThreadsTable.updatedAt
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.ThreadsTable.userId
import jp.ac.it_college.std.s23007.messageboard.presentation.from.*
import jp.ac.it_college.std.s23007.messageboard.application.service.MessageBoardUserDetailsService
import jp.ac.it_college.std.s23007.messageboard.application.service.ThreadService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

class ThreadsController {
    @RestController
    @RequestMapping("/threads")
    @CrossOrigin
    class ThreadController(
        private val service: ThreadService
    ) {
        @GetMapping("/list")
        fun getList(): GetThreadListResponse {
            val threadList = service.getList().map(::ThreadInfo)
            return GetThreadListResponse(threadList)
        }

        @PostMapping("/create")
        fun create(
            @RequestBody body: PostThreadRequest,
            @AuthenticationPrincipal user: MessageBoardUserDetailsService
        ): CreatedThreadResponse {
            val newId = service.newPost(body.title, body.message, user.id)
            return CreatedThreadResponse(newId)
        }

        @PutMapping("/update/{id}")
        fun update(
            @PathVariable id: Long,
            @RequestBody body: PutThreadUpdateRequest,
            @AuthenticationPrincipal user: MessageBoardUserDetailsService
        ): ThreadUpdateResponse {
            val thread = service.updateTitle(id, body.title, userId)
            return thread.run { ThreadUpdateResponse(id, title) }
        }

        @DeleteMapping("/delete/{id}")
        fun deleteThread(
            @PathVariable id: Long,
            @AuthenticationPrincipal user: MessageBoardUserDetailsService
        ): ThreadDeleteResponse {
            val result = service.deleteThread(id)
            return result.run {
                ThreadDeleteResponse(id, title, updatedAt)
            }
        }
    }
}