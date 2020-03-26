package com.hotel.ena.models;


import com.hotel.ena.repository.RacunRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component

public class Podaci {

    private RacunRepository racunRepozitorij;


    @Autowired
    public Podaci(RacunRepository racunRepozitorij) {

        this.racunRepozitorij = racunRepozitorij;

    }


    @EventListener


    public void dodaj(ApplicationReadyEvent event) {

        dodajRacune();
    }


    private void dodajRacune() {


        RacunEntity racun = new RacunEntity();


        racun.setCreatedBy("Amina");
        racun.setCost(1000);
        racun.setPaid(false);
        racun.setCreated("2020-09-08");

        racunRepozitorij.save(racun);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RacunEntity>> violations = validator.validate(racun);
        for (ConstraintViolation<RacunEntity> violation : violations) {
            final Logger log = LogManager.getLogger(Podaci.class.getName());

            log.error(violation.getMessage());
        }
    }


}

      

