package cloud.voiture.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int idvendeur;
    int idacheteur;
    String idannonce;
    double commission;
    Date date;

    public Historique(Long id, int idvendeur, int idacheteur, String idannonce, double commission, Date date) {
        this.id = id;
        this.idvendeur = idvendeur;
        this.idacheteur = idacheteur;
        this.idannonce = idannonce;
        this.commission = commission;
        this.date = date;
    }



    public Historique() {
    }

    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdvendeur() {
        return this.idvendeur;
    }

    public void setIdvendeur(int idvendeur) {
        this.idvendeur = idvendeur;
    }

    public int getIdacheteur() {
        return this.idacheteur;
    }

    public void setIdacheteur(int idacheteur) {
        this.idacheteur = idacheteur;
    }

    public String getIdannonce() {
        return this.idannonce;
    }

    public void setIdannonce(String idannonce) {
        this.idannonce = idannonce;
    }

    public Historique id(Long id) {
        setId(id);
        return this;
    }

    public Historique idvendeur(int idvendeur) {
        setIdvendeur(idvendeur);
        return this;
    }

    public Historique idacheteur(int idacheteur) {
        setIdacheteur(idacheteur);
        return this;
    }

    public Historique idannonce(String idannonce) {
        setIdannonce(idannonce);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Historique)) {
            return false;
        }
        Historique historique = (Historique) o;
        return Objects.equals(id, historique.id) && idvendeur == historique.idvendeur && idacheteur == historique.idacheteur && Objects.equals(idannonce, historique.idannonce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idvendeur, idacheteur, idannonce);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idvendeur='" + getIdvendeur() + "'" +
            ", idacheteur='" + getIdacheteur() + "'" +
            ", idannonce='" + getIdannonce() + "'" +
            "}";
    }



    public double getCommission() {
        return commission;
    }



    public void setCommission(double commission) {
        this.commission = commission;
    }



    public Date getDate() {
        return date;
    }



    public void setDate(Date date) {
        this.date = date;
    }

    
}
