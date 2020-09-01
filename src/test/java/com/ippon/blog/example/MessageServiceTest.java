package com.ippon.blog.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageConsumer messageConsumer;

    @BeforeEach
    public void beforeEach() {
        messageConsumer.resetMessage();
        messageConsumer.resetCountDownLatch();
    }

    @Test
    public void publishMessage() throws InterruptedException {

        Message message = new Message("source", "Hello World!");
        messageService.publishMessage(message);

        messageConsumer.getCountDownLatch().await(4, TimeUnit.SECONDS);

        assertNotNull(message);
        assertEquals(message.getMessage(), messageConsumer.getResultMessage().getMessage());
        assertEquals(message.getName(), messageConsumer.getResultMessage().getName());
    }
}
