//package ba.com.zira.template.dao.model.translation;
package com.hotel.ena.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Racun")
@NamedQuery(name = "RacunEntity.findAll", query = "SELECT t FROM RacunEntity t")
public class RacunEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "Racun_ID_GENERATOR", sequenceName = "Racun_SEQ",
             allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Racun_ID_GENERATOR")

    @NotNull
    private Long id;

    @NotNull
    private String user_id;
    @NotNull
    private String createdBy;
    @NotNull
    private String created;

    @NotNull
    private double cost;
    @NotNull
    private String reservation_id;

    @AssertTrue
    private Boolean paid;

    public RacunEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.user_id;
    }

    public void setUserId(final String userId){
        this.user_id = userId;
    }
    public String getReservationId() {
        return this.reservation_id;
    }

    public void setReservationId(final String reservationId){
        this.reservation_id = reservationId;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(final String created){
        this.created = created;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(final String createdBy){
        this.createdBy = createdBy;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(final double cost){
        this.cost = cost;
    }

    public Boolean getPaid() {
        return this.paid;
    }

    public void setPaid(final Boolean paid){
        this.paid = paid;
    }



}