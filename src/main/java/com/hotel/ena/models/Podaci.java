package com.hotel.ena.models;

import com.hotel.ena.repository.RacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

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

    }
}

      

