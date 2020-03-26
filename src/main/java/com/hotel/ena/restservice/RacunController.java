package com.hotel.ena.restservice;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.hotel.ena.accessingdatarest.Racun;
import com.hotel.ena.models.RacunEntity;
import org.springframework.web.bind.annotation.*;
import com.hotel.ena.repository.RacunRepository;

@RestController
public class RacunController {

    private static final String template = "Iznos: %f";
    private final AtomicLong counter = new AtomicLong();

    private final RacunRepository racunRepozitorij;

    RacunController(RacunRepository racunRepozitorij) {
        this.racunRepozitorij = racunRepozitorij;
    }

    // Aggregate root

    @GetMapping("/racun")
    List<RacunEntity> all() {
        return racunRepozitorij.findAll();
    }

    @PostMapping("/racun")
    RacunEntity newRacun(@RequestBody RacunEntity noviRacun) {
        return racunRepozitorij.save(noviRacun);
    }

    // Single item

    @GetMapping("/racun/{id}")
    RacunEntity one(@PathVariable Long id) {

        return racunRepozitorij.findById(id)
                .orElseThrow(() -> new RacunNotFoundException(id));
    }

    @PutMapping("/racun/{id}")
    RacunEntity zamijeniRacun(@RequestBody RacunEntity newRacun, @PathVariable Long id) {

        return racunRepozitorij.findById(id)
                .map(racunEntity -> {
                    racunEntity.setCost(newRacun.getCost());
                    racunEntity.setId(newRacun.getId());
                    racunEntity.setPaid(newRacun.getPaid());
                    racunEntity.setReservationId(newRacun.getReservationId());
                    racunEntity.setCreated(newRacun.getCreated());
                    racunEntity.setCreatedBy(newRacun.getCreatedBy());
                    racunEntity.setUserId(newRacun.getUserId());
                    return racunRepozitorij.save(racunEntity);
                })
                .orElseGet(() -> {
                    newRacun.setId(id);
                    return racunRepozitorij.save(newRacun);
                });
    }

    @DeleteMapping("/racun/{id}")
    void deleteRacun(@PathVariable Long id) {
        racunRepozitorij.deleteById(id);
    }


    @PostMapping("/racun")

    public Racun racun(@RequestParam(value = "iznos", defaultValue = "0") double iznos) {
        return new Racun(counter.incrementAndGet(), String.format(template, iznos));
    }

    @GetMapping("/racun")

    public List<RacunEntity>  findRacun() {
        return racunRepozitorij.findAll();
    }

}


