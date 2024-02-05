package cloud.voiture.model;

public class NombreUser {
    private long nbadmin;
    private long nbuser;
    private long total;

    

    public NombreUser(long nbadmin, long nbuser) {
        this.nbadmin = nbadmin;
        this.nbuser = nbuser;
        this.total = nbadmin+nbuser;
    }
    public long getNbadmin() {
        return nbadmin;
    }
    public void setNbadmin(long nbadmin) {
        this.nbadmin = nbadmin;
    }
    public long getNbuser() {
        return nbuser;
    }
    public void setNbuser(long nbuser) {
        this.nbuser = nbuser;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    
}
