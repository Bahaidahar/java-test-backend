package com.baha.test

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class LoginRequest(val username: String, val password: String)
data class TokenResponse(val token: String)

@RestController
class AuthController {

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): ResponseEntity<Any> {
        // Мок: один пользователь user/password
        return if (req.username == "user" && req.password == "password") {
            ResponseEntity.ok(TokenResponse("mock-token-123"))
        } else {
            ResponseEntity.status(401).body(mapOf("error" to "Invalid credentials"))
        }
    }

    @GetMapping("/hello")
    fun hello(@RequestHeader("Authorization") auth: String?): ResponseEntity<String> {
        return if (auth == "Bearer mock-token-123") {
            ResponseEntity.ok("Hello, user!")
        } else {
            ResponseEntity.status(403).body("Forbidden")
        }
    }
}
