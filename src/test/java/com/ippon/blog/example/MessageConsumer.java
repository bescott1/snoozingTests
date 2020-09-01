package com.ippon.blog.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageConsumer {

    Message resultMessage;
    CountDownLatch countDownLatch;

    @EventListener
    public void consumeMessage(Message message) {
        resultMessage = message;
        countDownLatch.countDown();
    }

    public void resetMessage() {
        resultMessage = null;
    }

    public void resetCountDownLatch() {
        countDownLatch = new CountDownLatch(1);
    }

    public Message getResultMessage() {
        return resultMessage;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
