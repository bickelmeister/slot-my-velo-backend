package de.slotmyvelo.auth.web.controller

import de.slotmyvelo.auth.application.port.`in`.LoginCommand
import de.slotmyvelo.auth.application.port.`in`.LoginUseCase
import de.slotmyvelo.auth.application.port.`in`.RegisterUseCase
import de.slotmyvelo.auth.web.AuthDtoMapper
import de.slotmyvelo.auth.web.dto.RegisterRequest
import de.slotmyvelo.auth.web.dto.AuthUserResponse
import de.slotmyvelo.auth.web.dto.LoginRequest
import de.slotmyvelo.auth.web.dto.LoginResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/auth")
class AuthController(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthUserResponse> {
        val command = AuthDtoMapper.toCommand(request)
        val user = registerUseCase.register(command)
        val response = AuthDtoMapper.toResponse(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val token = loginUseCase.login(
            LoginCommand(
                email = request.email,
                password = request.password
            )
        )
        return ResponseEntity.ok(token)
    }
}
