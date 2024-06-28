package jp.ac.it_college.std.s23007.messageboard.presentation.contoroller

import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.UserTable.email
import jp.ac.it_college.std.s23007.messageboard.infrastructure.database.dao.UserTable.password
import jp.ac.it_college.std.s23007.messageboard.presentation.from.GetUserInfoResponse
import jp.ac.it_college.std.s23007.messageboard.presentation.from.PostUserRegisterRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
@CrossOrigin
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(@RequestBody user: PostUserRegisterRequest) {
        user.run {
            userService.register(viewName, email, password)
        }
    }

    @GetMapping("/info")
    fun getInfo(
        @AuthenticationPrincipal user: MessageBoardUserDetails
    ): GetUserInfoResponse {
        return userService.find(user.id).run {
            GetUserInfoResponse(id, viewName)
        }
    }
}