package no.java.elasticsearch

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import java.net.InetAddress

@SpringBootApplication
@ComponentScan("no.java.elasticsearch")
class Application

fun main(args: Array<String>) {

    SpringApplication.run(Application::class.java, *args)
}