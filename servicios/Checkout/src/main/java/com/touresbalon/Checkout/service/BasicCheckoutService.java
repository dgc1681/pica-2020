package com.touresbalon.Checkout.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.touresbalon.Checkout.engine.Consumer;
import com.touresbalon.Checkout.model.Checkout;
import com.touresbalon.Checkout.model.CheckoutResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class BasicCheckoutService {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    ObjectMapper mapperObj = new ObjectMapper();

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(final String transaccion) throws IOException {

        logger.info(String.format("Consuming -> %s", transaccion));

        JsonNode objTransaccion = mapperObj.readTree(transaccion);
        String cardNumber = objTransaccion.get("card").asText();
        String expiryDate = objTransaccion.get("expiry_date").asText();
        Integer orderNumber = objTransaccion.get("order").asInt();

        Checkout checkout = new Checkout(cardNumber, expiryDate, orderNumber);
        CheckoutResponseDTO sendCheckout = new CheckoutResponseDTO(checkout.getCard(), checkout.getExpiry_date(), checkout.getOrder());

        final String uri = "http://localhost:8080/api/touresbalon/pasarela/validating/"+sendCheckout.getCard()+"?expiry_date="+sendCheckout.getExpiry_date();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        System.out.println(jsonObject.get("message"));
    }
}
