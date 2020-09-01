package com.ippon.blog.example;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MessageService {

    ApplicationEventPublisher eventPublisher;

    public MessageService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Async
    public void publishMessage(Message message) {
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(3000));
            eventPublisher.publishEvent(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
