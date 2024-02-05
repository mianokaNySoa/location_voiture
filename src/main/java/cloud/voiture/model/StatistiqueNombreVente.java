package cloud.voiture.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_nb_vente")
public class StatistiqueNombreVente {

    @Id
    private Long id;
    private int annee;
    private int mois;
    private double nombre;

    public StatistiqueNombreVente() {
    }

    public StatistiqueNombreVente(Long id, int annee, int mois, double nombre) {
        this.id = id;
        this.annee = annee;
        this.mois = mois;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }

    
    

}
