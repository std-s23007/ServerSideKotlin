package jp.ac.it_college.std.s23007.messageboard.presentation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

class SecurityConfig {
    @Configuration
    @EnableWebSecurity
    class SecurityConfig {

        @Bean
        @Order(1)
        fun configure(http: HttpSecurity): SecurityFilterChain {
            http {
                authorizeHttpRequests {
                    authorize(anyRequest, permitAll)
                }
            }
            return http.build()
        }
    }
}