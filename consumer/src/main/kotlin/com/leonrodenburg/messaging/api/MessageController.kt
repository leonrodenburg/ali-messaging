package com.leonrodenburg.messaging.api

import com.leonrodenburg.messaging.service.MnsConsumer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class MessageController (private val mnsConsumer: MnsConsumer) {

    @GetMapping("/message")
    fun lastMessage(): Mono<Message> = Mono.just(Message(mnsConsumer.lastMessage))

    data class Message(val lastMessage: String?)
}