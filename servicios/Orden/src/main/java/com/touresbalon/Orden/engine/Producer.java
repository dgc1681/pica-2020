package com.touresbalon.Orden.engine;

import com.touresbalon.Orden.model.Orden;
import com.touresbalon.Orden.model.OrdenResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "checkout";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(OrdenResponseDTO transaccion) {
        logger.info(String.format("Producing -> %s", transaccion));
        this.kafkaTemplate.send(TOPIC, transaccion);
    }
}
