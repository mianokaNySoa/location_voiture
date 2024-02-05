package cloud.voiture.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_stat_commission")
public class StatistiqueCommission {

    @Id
    private Long id;
    private int annee;
    private int mois;
    private double total;

    public StatistiqueCommission() {
    }

    public StatistiqueCommission(Long id, int annee, int mois, double total) {
        this.id = id;
        this.annee = annee;
        this.mois = mois;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    

}
