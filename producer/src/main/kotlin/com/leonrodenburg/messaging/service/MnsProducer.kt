package com.leonrodenburg.messaging.service

import com.aliyun.mns.client.CloudQueue
import com.aliyun.mns.client.CloudTopic
import com.aliyun.mns.model.Base64TopicMessage
import com.aliyun.mns.model.Message
import com.aliyun.mns.model.Message.MessageBodyType.*
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MnsProducer(private val queue: CloudQueue, private val topic: CloudTopic) {
    private val logger = LoggerFactory.getLogger(MnsProducer::class.java)
    private var topicMessageCount = 1
    private var queueMessageCount = 1

    @Scheduled(fixedDelay = 12000, initialDelay = 12000)
    fun produceMessageOnTopic() {
        logger.info("Publishing on topic...")

        val message = Base64TopicMessage()
        message.messageBody = "Topic message $topicMessageCount"
        topic.publishMessage(message)

        topicMessageCount++
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    fun produceMessageOnQueue() {
        logger.info("Publishing on queue...")

        val message = Message()
        message.setMessageBody("Queue message $queueMessageCount", BASE64)
        queue.putMessage(message)

        queueMessageCount++
    }
}