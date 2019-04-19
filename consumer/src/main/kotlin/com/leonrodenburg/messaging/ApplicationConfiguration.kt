package com.leonrodenburg.messaging

import com.aliyun.mns.client.CloudAccount
import com.aliyun.mns.client.CloudQueue
import com.aliyun.mns.client.MNSClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class ApplicationConfiguration

@Configuration
class MnsConfiguration {

    @Bean
    fun queue(@Value("\${alicloud.queue.name}") queueName: String, mnsClient: MNSClient): CloudQueue = mnsClient.getQueueRef(queueName)

    @Bean
    fun cloudAccount(
            @Value("\${alicloud.accesskey.id}") accessKeyId: String,
            @Value("\${alicloud.accesskey.secret}") accessKeySecret: String,
            @Value("\${alicloud.mns.endpoint}") mnsEndpoint: String
    ): CloudAccount =
            CloudAccount(
                    accessKeyId,
                    accessKeySecret,
                    mnsEndpoint
            )

    @Bean
    fun mnsClient(cloudAccount: CloudAccount): MNSClient = cloudAccount.mnsClient
}