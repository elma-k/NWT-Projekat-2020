package com.example.hotel.ena.rest;
import com.example.hotel.ena.dto.Racun;
import com.example.hotel.ena.models.RacunEntity;
import com.example.hotel.ena.repository.RacunRepository;
import com.example.hotel.ena.validation.RequestValidation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@RestController
@RequestMapping("/racun")
    public class RacunController {
private RequestValidation requestValidation;
        private static final String template = "Iznos: %f";
        private final AtomicLong counter = new AtomicLong();

        private final RacunRepository racunRepozitorij;

        // Aggregate root

        @GetMapping()
        List<RacunEntity> all() {
            return racunRepozitorij.findAll();
        }

        @PostMapping()
        String newRacun(@RequestBody RacunEntity noviRacun) {
            if(requestValidation.validateIznos(noviRacun.getCost())==null) {
                racunRepozitorij.save(noviRacun);
                return "Racun created successfully";
            }
            else {
                return requestValidation.validateIznos(noviRacun.getCost());
            }
        }

        // Single item

     /*   @GetMapping("/{id}")
        RacunEntity one(@PathVariable Long id) {

            return racunRepozitorij.findById(id);
                    //.orElseThrow(() -> new RacunNotFoundException(id));
        }*/

        @PutMapping("/{id}")
        RacunEntity zamijeniRacun(@RequestBody RacunEntity newRacun, @PathVariable Long id) {

            return racunRepozitorij.findById(id)
                    .map(racunEntity -> {
                        racunEntity.setCost(newRacun.getCost());
                        //racunEntity.setId(newRacun.getId());
                        racunEntity.setPaid(newRacun.getPaid());
                        racunEntity.setReservationId(newRacun.getReservationId());
                        racunEntity.setCreated(newRacun.getCreated());
                        racunEntity.setCreatedBy(newRacun.getCreatedBy());
                        racunEntity.setUserId(newRacun.getUserId());
                        return racunRepozitorij.save(racunEntity);
                    })
                    .orElseGet(() -> {
                        //newRacun.setId(id);
                        return racunRepozitorij.save(newRacun);
                    });
        }

        @DeleteMapping("/{id}")
        void deleteRacun(@PathVariable Long id) {
            racunRepozitorij.deleteById(id);
        }


        @PostMapping("/racun/iznos")

        public Racun racun(@RequestParam(value = "iznos", defaultValue = "0") double iznos) {
            return new Racun(counter.incrementAndGet(), String.format(template, iznos));
        }

        @GetMapping("/racun")

        public List<RacunEntity> findRacun() {
            return racunRepozitorij.findAll();
        }

    }


