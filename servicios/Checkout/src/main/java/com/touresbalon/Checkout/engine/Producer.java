package com.touresbalon.Checkout.engine;

import com.touresbalon.Checkout.model.Checkout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    /*private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Checkout checkout) {
        logger.info(String.format("Producing -> %s", checkout));
        this.kafkaTemplate.send(TOPIC, checkout);
    }*/
}