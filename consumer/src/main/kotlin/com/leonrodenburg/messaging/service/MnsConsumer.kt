package com.leonrodenburg.messaging.service

import com.aliyun.mns.client.CloudQueue
import com.aliyun.mns.model.Message
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MnsConsumer(private val queue: CloudQueue) {
    private val logger = LoggerFactory.getLogger(MnsConsumer::class.java)
    final var lastMessage: String? = null
        private set

    @Scheduled(fixedDelay = 1000)
    fun consumeMessages() {
        val message: Message? = queue.popMessage(10)

        message?.let {
            lastMessage = it.messageBodyAsString
            logger.info("Received message: $lastMessage")
            queue.deleteMessage(it.receiptHandle)
        }
    }
}