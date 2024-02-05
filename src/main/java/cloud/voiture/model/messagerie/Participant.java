package cloud.voiture.model.messagerie;

public class Participant {
    int id_utilisateur;
    String username;

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Participant() {
    }

    public Participant(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Participant(int id_utilisateur, String username) {
        this.id_utilisateur = id_utilisateur;
        this.username = username;
    }
}
