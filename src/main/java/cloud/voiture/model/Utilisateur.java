package cloud.voiture.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Date dtn;
    private String mdp;
    @Column(name = "isadmin")
    private int isadmin;
    private int actif;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getisAdmin() {
        return isadmin;
    }

    public void setisAdmin(int isadmin) {
        this.isadmin = isadmin;
    }

    public String getRole() {
        System.out.println("isadmin"+isadmin);
        if (this.getisAdmin() == 1) {
            return "ADMIN";
        }
            return "USER";
    }

    public void setRole(String role){
        if (role.equalsIgnoreCase("ROLE_ADMIN")) {
            this.setisAdmin(1);
        } else {
            this.setisAdmin(0);
        }
    }

    public Utilisateur() {
    }

    public Utilisateur(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Utilisateur(Integer id, String nom, String prenom, String email, Date dtn, String mdp, int isadmin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dtn = dtn;
        this.mdp = mdp;
        this.isadmin = isadmin;
    }


    public int getActif() {
        return this.actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

}
