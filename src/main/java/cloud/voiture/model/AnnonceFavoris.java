package cloud.voiture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "annoncefavoris")
public class AnnonceFavoris {
    @Id
    private String id;
    private String idannonce;
    private int idutilisateur;
    
    

    public AnnonceFavoris() {
    }

    public AnnonceFavoris(String id, String idannonce, int idutilisateur) {
        this.id = id;
        this.idannonce = idannonce;
        this.idutilisateur = idutilisateur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdannonce() {
        return idannonce;
    }

    public void setIdannonce(String idannonce) {
        this.idannonce = idannonce;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

}
