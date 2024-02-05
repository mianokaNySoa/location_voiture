package cloud.voiture.model.filtre;

import java.util.Date;

import org.springframework.data.mongodb.core.query.Criteria;

public class DateFiltre {
    Date dateMin;
    Date dateMax;

    public Date getDateMin() {
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    public DateFiltre() {
    }

    public DateFiltre(Date dateMin, Date dateMax) {
        this.dateMin = dateMin;
        this.dateMax = dateMax;
    }

    public Criteria getCritere() {
        Criteria critere = new Criteria();
        if (this.getDateMax() != null) {
            critere = Criteria.where("date").lte(this.getDateMax());
            if (this.getDateMin() != null) {
                critere = critere.andOperator(Criteria.where("date").gte(this.getDateMin()));
            }
            return critere;
        }
        if (this.getDateMin() != null) {
            critere = Criteria.where("date").gte(this.getDateMin());
            return critere;
        }
        return null;
    }
}
