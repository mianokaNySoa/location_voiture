package cloud.voiture.model.request;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cloud.voiture.model.filtre.DateFiltre;
import cloud.voiture.model.filtre.PrixFiltre;

public class FiltreAnnonceReq {
    private String motCle;
    private int categorie;
    private int marque;
    private int pays;
    private int type;
    private int carburant;
    private PrixFiltre prixFiltre;
    private DateFiltre dateFiltre;

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getMarque() {
        return marque;
    }

    public void setMarque(int marque) {
        this.marque = marque;
    }

    public int getPays() {
        return pays;
    }

    public void setPays(int pays) {
        this.pays = pays;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCarburant() {
        return carburant;
    }

    public void setCarburant(int carburant) {
        this.carburant = carburant;
    }

    public PrixFiltre getPrixFiltre() {
        return prixFiltre;
    }

    public void setPrixFiltre(PrixFiltre prixFiltre) {
        this.prixFiltre = prixFiltre;
    }

    public DateFiltre getDateFiltre() {
        return dateFiltre;
    }

    public void setDateFiltre(DateFiltre dateFiltre) {
        this.dateFiltre = dateFiltre;
    }

    public Query giveQueryFilter() {
        Query query = new Query();
        if (this.getMotCle() != null) {
            query.addCriteria(Criteria.where("description").regex("(?i).*" + this.getMotCle() + ".*"));
        }
        if (this.getCategorie() != 0) {
            query.addCriteria(Criteria.where("categorie.id").is(this.getCategorie()));
        }
        if (this.getMarque() != 0) {
            query.addCriteria(Criteria.where("marque.id").is(this.getMarque()));
        }
        if (this.getType() != 0) {
            query.addCriteria(Criteria.where("type.id").is(this.getType()));
        }
        if (this.getPays() != 0) {
            query.addCriteria(Criteria.where("marque.pays.id").is(this.getPays()));
        }
        if (this.getCarburant() != 0) {
            query.addCriteria(Criteria.where("carburant.id").is(this.getCarburant()));
        }
        Criteria dateCriteria = this.getDateFiltre().getCritere();
        if (dateCriteria != null) {
            query.addCriteria(dateCriteria);
        }
        Criteria prixCriteria = this.getPrixFiltre().getCritere();
        if (prixCriteria != null) {
            query.addCriteria(prixCriteria);
        }
        return query;
    }
}
