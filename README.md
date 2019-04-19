# Alibaba Cloud Message Service demo

Example application demonstrating how to use queues and topics.

## Starting the demo

To start the demo locally, you should first configure your Alibaba Cloud credentials in a file called `credentials.env` in the same directory as `docker-compose.yaml`:

```
ALICLOUD_ACCESSKEY_ID=my-access-key
ALICLOUD_ACCESSKEY_SECRET=my-access-key-secret
ALICLOUD_MNS_ENDPOINT=https://my-account-id.mns.eu-central-1.aliyuncs.com/
```

You can find your access keys [here](https://usercenter.console.aliyun.com/#/manage/ak). To find your MNS endpoint, go to the [Message Service dashboard](https://mns-eu-central-1.console.aliyun.com/#/list/eu-central-1) and click 'Get Endpoint'.

When you have configured your credentials, make sure your account contains three queues (`Consumer1`, `Consumer2`, `Consumer3`) and a topic (`AllConsumers`) that all three queues are subscribed to. You can use the [Message Service dashboard](https://mns-eu-central-1.console.aliyun.com/#/list/eu-central-1) to set that up.

After you have configured your credentials and created the queues and topics, you can start the demo with the following command:

```bash
./run.sh
```

This should build the producer and consumer (make sure Maven is installed) and then start the containers. A single producer and three consumers will be fired up. The producer will publish to the queue named `Consumer2` every 5 seconds, and publish to the `AllConsumers` topic every 12 seconds.

## Emulating downtime

To emulate downtime for consumer 2 (the service that receives direct messages from the publisher and messages from the topic), use the following command:

```bash
docker-compose stop consumer-2
```

This should stop the consumer. In the logging, you won't see the queue messages being received anymore. However, the messages are still being put on the queue. If you start the consumer again, it will happily continue processing all the messages it had missed:

```
docker-compose start consumer-2
```

Prepare for a large intake of messages on the second consumer! As the intake is throttled at 1 message per second, it might take some time to catch up. Also note that there is no ordering guarantee in the messages. This means that a message posted to the queue later might be received earlier. You can see this happening in the logging output, as the numbering might not be in the right order during catchup.
