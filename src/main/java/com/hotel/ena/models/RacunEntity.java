package ba.com.zira.template.dao.model.translation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "Racun")
@NamedQuery(name = "RacunEntity.findAll", query = "SELECT t FROM RacunEntity t")
public class RacunEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "Racun_ID_GENERATOR", sequenceName = "Racun_SEQ",
             allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Racun_ID_GENERATOR")
    private Long id;

   private String user_id;
private String createdBy;
private Date created;
private BigDecimal cost;
private String reservation_id;
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
        return this.userId;
    }

    public void setUserId(final String userId){
        this.userId = userId;
    }
    public String getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(final String reservationId){
        this.reservationId = reservationId;
    }

 public Date getCreated() {
        return this.created;
    }

    public void setCreated(final Date created){
        this.created = created;
    }

public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(final String createdBy){
        this.createdBy = createdBy;
    }

public BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(final BigDecimal cost){
        this.cost = cost;
    }

public Boolean getPaid() {
        return this.paid;
    }

    public void setPaid(final Boolean paid){
        this.paid = paid;
    }

   

}