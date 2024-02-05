package cloud.voiture.model.filtre;

import org.springframework.data.mongodb.core.query.Criteria;

public class PrixFiltre {
    private double prixMin;
    private double prixMax;

    public double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }

    public PrixFiltre() {
    }

    public PrixFiltre(double prixMin, double prixMax) {
        this.prixMin = prixMin;
        this.prixMax = prixMax;
    }

    public Criteria getCritere() {
        Criteria critere = new Criteria();
        if (this.getPrixMax() != 0) {
            critere = Criteria.where("prix").lte(this.getPrixMax());
            if (this.getPrixMin() != 0) {
                critere = critere.andOperator(Criteria.where("prix").gte(this.getPrixMin()));
            }
            return critere;
        }
        if (this.getPrixMin() != 0) {
            critere = Criteria.where("prix").gte(this.getPrixMin());
            return critere;
        }
        return null;
    }
}
