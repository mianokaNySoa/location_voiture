package cloud.voiture.model;

public class AnnoncePostRequest {
    
    private Annonce annonce;
    private String[] image;

    public Annonce getAnnonce() {
        return annonce;
    }
    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
    public String[] getImage() {
        return image;
    }
    public void setImage(String[] image) {
        this.image = image;
    }

    
}

