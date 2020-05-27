package com.example.hotel.ena.configuration;

import com.example.hotel.ena.models.*;
import com.example.hotel.ena.repository.*;
import lombok.extern.slf4j.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.stereotype.*;

@Service
@Slf4j
public class ReceiveMessageHandler {
RezervacijaRepository rezervacijaRepository;
RabbitTemplate rabbitTemplate;
    public void handleMessage(String messageBody) {
        log.info("HandleMessage!!!");
        log.info(messageBody);
        if (!messageBody.contains("failed")) {
            try {
                RezervacijaEntity racunEntity = rezervacijaRepository.findByRacunId(Long.valueOf(messageBody)).get();
                racunEntity.setDone(true);
            } catch (Exception e) {
                rabbitTemplate.convertAndSend("failed " + messageBody);
            }
        }
        else{
            RezervacijaEntity rezervacijaEntity=rezervacijaRepository.findByRacunId(Long.valueOf(messageBody.substring(7))).get();
            rezervacijaEntity.setDone(false);
        }
    }

}