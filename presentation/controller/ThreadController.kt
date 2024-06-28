package presentation.controller

import com.example.bulletinboard.model.Thread
import com.example.bulletinboard.service.ThreadService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/threads")
class ThreadController(private val threadService: ThreadService) {

    @PostMapping
    fun createThread(
        @RequestBody request: CreateThreadRequest,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<Thread> {
        val thread = threadService.createThread(request, userDetails.username)
        return ResponseEntity.ok(thread)
    }

    @GetMapping
    fun getAllThreads(): ResponseEntity<List<Thread>> {
        val threads = threadService.getAllThreads()
        return ResponseEntity.ok(threads)
    }

    @PutMapping("/{threadId}")
    fun updateThreadTitle(
        @PathVariable threadId: Long,
        @RequestBody request: UpdateThreadTitleRequest,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<Thread> {
        val updatedThread = threadService.updateThreadTitle(threadId, request.title, userDetails.username)
        return ResponseEntity.ok(updatedThread)
    }

    @DeleteMapping("/{threadId}")
    fun deleteThread(
        @PathVariable threadId: Long,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<String> {
        threadService.deleteThread(threadId, userDetails.username)
        return ResponseEntity.ok("Thread deleted successfully")
    }
}
