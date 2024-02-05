package cloud.voiture.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity

public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "La valeur minimale doit être supérieur à 0")
    double min;
    double max;

    @NotNull(message = "Le champ valeur ne peut pas être nul")
    @DecimalMin(value = "0.0", inclusive = false, message = "La valeur doit être supérieur à 0")
    double valeur;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "La date doit etre anterieur ou egale a aujourd'hui")
    Date dates;

    int etat;

    public Commission() {
    }

    public Commission(Long id, double valeur, Date dates, int etat) {
        this.id = id;
        this.valeur = valeur;
        this.dates = dates;
        this.etat = etat;
    }


    public Commission(Long id, double min, double max, double valeur, Date dates, int etat) throws Exception{
        this.id = id;
        this.min = min;
        this.max = max;
        this.valeur = valeur;
        this.dates = dates;
        this.etat = etat;
    }

    public double getMin() {
        return this.min;
    }

    public void setMin(double min) throws Exception{
        
        this.min = min;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(double max) throws Exception{
        if(max < min){
            throw new Exception("Valeur maximale doit etre superieur a la valeur minimale");
        }

        
        this.max = max;
    }

    public Commission min(double min) throws Exception {
        setMin(min);
        return this;
    }

    public Commission max(double max)throws Exception {
        setMax(max);
        return this;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValeur() {
        return this.valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Date getDates() {
        return this.dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getEtat() {
        return this.etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Commission id(Long id) {
        setId(id);
        return this;
    }

    public Commission valeur(double valeur) {
        setValeur(valeur);
        return this;
    }

    public Commission dates(Date dates) {
        setDates(dates);
        return this;
    }

    public Commission etat(int etat) {
        setEtat(etat);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Commission)) {
            return false;
        }
        Commission commission = (Commission) o;
        return Objects.equals(id, commission.id) && valeur == commission.valeur
                && Objects.equals(dates, commission.dates) && etat == commission.etat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valeur, dates, etat);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", valeur='" + getValeur() + "'" +
                ", dates='" + getDates() + "'" +
                ", etat='" + getEtat() + "'" +
                "}";
    }

    @PrePersist
    public void prePersist() {
        if (this.dates == null) {
            this.dates = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (this.dates == null) {
            this.dates = new Date();
        }
    }

    

}
