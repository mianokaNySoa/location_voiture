package cloud.voiture.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "annonce")
public class Annonce {

    @Id
    private String id;
    private String nomVoiture;
    private Marque marque;
    private Categorie categorie;
    private Types type;
    private double prix;
    private Date date;
    private Utilisateur utilisateur;
    private Carburant carburant;
    private String description;
    private List<Photo> photo = new ArrayList<>();
    private int etat;

    public Annonce(String id, String nomVoiture, Marque marque, Categorie categorie, Types type, double prix, Date date,
            Utilisateur utilisateur, Carburant carburant, String description, List<Photo> photo, int etat) {
        this.id = id;
        this.nomVoiture = nomVoiture;
        this.marque = marque;
        this.categorie = categorie;
        this.type = type;
        this.prix = prix;
        this.date = date;
        this.utilisateur = utilisateur;
        this.carburant = carburant;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
    }

    public String getNomVoiture() {
        return nomVoiture;
    }

    public void setNomVoiture(String nomVoiture) {
        this.nomVoiture = nomVoiture;
    }

    public Annonce() {
    }

    public Carburant getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws Exception{
        if(prix < 0){
            throw new Exception("prix doit etre positif ou nul");
        }
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setIdutilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
