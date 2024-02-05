package cloud.voiture.model;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    @ManyToOne
    @JoinColumn(name="idpays") // tsy mety vo idPays
    private Pays pays;
    private int etat;

    public Marque(int id, String libelle, Pays pays, int etat) {
        this.id = id;
        this.libelle = libelle;
        this.pays = pays;
        this.etat = etat;
    }


    public Marque() {
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }



    public Pays getPays() {
        return pays;
    }



    public void setPays(Pays pays) {
        this.pays = pays;
    }

    
}
