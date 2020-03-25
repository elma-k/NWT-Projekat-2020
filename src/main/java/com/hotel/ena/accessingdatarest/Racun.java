package com.hotel.ena.accessingdatarest;

import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Racun {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //private String Iznos;
    private BigDecimal iznos;

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }


}