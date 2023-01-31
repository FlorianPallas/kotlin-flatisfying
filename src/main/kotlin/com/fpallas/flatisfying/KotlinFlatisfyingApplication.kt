package com.fpallas.flatisfying

import com.fpallas.flatisfying.model.create.CreateGroupData
import com.fpallas.flatisfying.model.create.CreatePurchaseData
import com.fpallas.flatisfying.model.create.CreateShareData
import com.fpallas.flatisfying.model.create.CreateUserData
import com.fpallas.flatisfying.repository.UserRepository
import com.fpallas.flatisfying.service.GroupService
import com.fpallas.flatisfying.service.PurchaseService
import com.fpallas.flatisfying.service.ShareService
import com.fpallas.flatisfying.service.UserService
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
class KotlinFlatisfyingApplication(val shareService: ShareService) {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info().title("Flatisfying API").version("v1")
                    .license(License().name("MIT").url("https://flatisfying.fpallas.com"))
            )
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
            }
        }
    }

    @Bean
    fun demo(
        userService: UserService,
        groupService: GroupService,
        userRepository: UserRepository,
        purchaseService: PurchaseService,
    ): CommandLineRunner {
        return CommandLineRunner {
            val florian = userService.create(CreateUserData("Florian"))
            val nicolas = userService.create(CreateUserData("Nicolas"))
            val lukas = userService.create(CreateUserData("Lukas"))
            val juli = userService.create(CreateUserData("Juli"))

            val wg = groupService.create(CreateGroupData("Durlach WG"))
            florian.group = wg
            nicolas.group = wg
            lukas.group = wg
            juli.group = wg
            userRepository.saveAll(listOf(florian, nicolas, lukas, juli))

            val groceries = purchaseService.create(wg, CreatePurchaseData("Groceries", 125.00, null))
            shareService.create(wg, groceries, florian, CreateShareData(35.00))
            shareService.create(wg, groceries, nicolas, CreateShareData(40.00))
            shareService.create(wg, groceries, juli, CreateShareData(20.00))
            shareService.create(wg, groceries, lukas, CreateShareData(30.00))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinFlatisfyingApplication>(*args)
}
