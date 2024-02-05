package cloud.voiture.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Carburant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String libelle;
    int etat;

    public Carburant() {
    }

    public Carburant(Long id, String libelle, int etat) {
        this.id = id;
        this.libelle = libelle;
        this.etat = etat;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getEtat() {
        return this.etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Carburant id(Long id) {
        setId(id);
        return this;
    }

    public Carburant libelle(String libelle) {
        setLibelle(libelle);
        return this;
    }

    public Carburant etat(int etat) {
        setEtat(etat);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Carburant)) {
            return false;
        }
        Carburant carburant = (Carburant) o;
        return Objects.equals(id, carburant.id) && Objects.equals(libelle, carburant.libelle) && etat == carburant.etat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, etat);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", libelle='" + getLibelle() + "'" +
                ", etat='" + getEtat() + "'" +
                "}";
    }

}
