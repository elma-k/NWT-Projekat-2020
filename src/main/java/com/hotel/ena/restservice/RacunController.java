package com.hotel.ena.restservice;

import java.math.BigDecimal;


        import java.util.concurrent.atomic.AtomicLong;

        import org.springframework.web.bind.annotation.GetMapping;
        import com.hotel.ena.repository.RacunRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacunController {

    private static final String template = "Iznos: %d";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private RacunRepository racunRepozitorij;


    @PostMapping("/racun")

    public Racun racun(@RequestParam(value = "iznos", defaultValue = "0.0") BigDecimal iznos) {
        return new Racun(counter.incrementAndGet(), BigDecimal.format(template, iznos));
    }

    @GetMapping("/racun")

    public ListRacuna findRacun() {
        return racunRepozitorij.findAll();
    }


}