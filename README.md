# Alibaba Cloud Message Service demo

Example application demonstrating how to use queues and topics.

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
