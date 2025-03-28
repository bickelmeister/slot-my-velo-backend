package de.slotmyvelo.user.web.controller

import de.slotmyvelo.auth.application.exception.InvalidTokenException
import de.slotmyvelo.user.application.port.`in`.GetUserProfileUseCase
import de.slotmyvelo.user.web.UserDtoMapper
import de.slotmyvelo.user.web.dto.UserProfileResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val getUserProfileUseCase: GetUserProfileUseCase
) {

    @GetMapping("/me")
    fun getUserProfile(@AuthenticationPrincipal jwt: Jwt): UserProfileResponse {
        val sub = jwt.subject ?: throw InvalidTokenException()
        val userId = sub.toLongOrNull() ?: throw InvalidTokenException()
        val profile = getUserProfileUseCase.getProfile(userId)
        return UserDtoMapper.toResponse(profile)
    }
}
