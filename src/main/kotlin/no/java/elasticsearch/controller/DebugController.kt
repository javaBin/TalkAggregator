package no.java.elasticsearch.controller

import com.auth0.AuthenticationController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.View
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpServletRequest
import com.auth0.jwt.JWT
import no.java.elasticsearch.service.LoadDataScheduler
import no.java.elasticsearch.service.TokenAuthentication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping


@RestController
@RequestMapping("/api/debug")
class DebugController(private val authenticationController: AuthenticationController,
                      private val loadDataScheduler: LoadDataScheduler) {

    @GetMapping("load")
    fun loadFromSleepingpill() {
        loadDataScheduler.loadData()
    }

    @GetMapping("/redirect")
    fun auth0redirect(httpServletRequest: HttpServletRequest) : View{

        return RedirectView(authenticationController.buildAuthorizeUrl(httpServletRequest, "http://localhost:8080/callback")
                .withAudience(String.format("https://%s/userinfo", "javabin.eu.auth0.com"))
                .withScope("openid")
                .build())
    }

    @GetMapping("/callback")
    fun auth0callback(httpServletRequest: HttpServletRequest) {

        val tokens = authenticationController.handle(httpServletRequest)
        val token = JWT.decode(tokens.idToken)
        val tokenAuthentication = TokenAuthentication(token)
        System.out.println("Boo")
    }

    companion object {
        val LOGGER:Logger = LoggerFactory.getLogger(DebugController::class.java)
    }
}