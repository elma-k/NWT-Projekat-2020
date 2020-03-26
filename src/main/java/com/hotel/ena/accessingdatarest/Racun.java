package com.hotel.ena.accessingdatarest;

import lombok.Data;
import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Racun {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private String Iznos;
    private double iznos;

    public Racun() {}

    public Racun(double iznos, Long id) {
        this.iznos = iznos;
        this.id = id;
    }

    public Racun(Long incrementAndGet, String format) {

    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }


}
