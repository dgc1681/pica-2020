package com.touresbalon.Orden.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.Orden.engine.Producer;
import com.touresbalon.Orden.model.Orden;
import com.touresbalon.Orden.model.OrdenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/touresbalon/orden")
public class OrdenREST {
    private final Producer producer;

    @Autowired
    OrdenREST(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("card") String card, @RequestParam("expiry_date") String expiry_date, @RequestParam("order") Integer id_order) {
        Orden orden = new Orden();
        orden.setCard(card);
        orden.setExpiry_date(expiry_date);
        orden.setOrder(id_order);

        OrdenResponseDTO transaccion = new OrdenResponseDTO(orden.getCard(), orden.getExpiry_date(), orden.getOrder());
        System.out.println("Hola "+card);
        this.producer.sendMessage(transaccion);
    }
}
