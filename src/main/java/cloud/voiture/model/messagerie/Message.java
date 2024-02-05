package cloud.voiture.model.messagerie;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Message {
    private int id_utilisateur;
    private Date date;
    private String contenu;

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate() {
        // String desiredTimeZone = "Indian/Antananarivo";
        // LocalDateTime localDateTime = LocalDateTime.of(this.date.getYear() + 1900,
        // this.date.getMonth() + 1,
        // this.date.getDate(),
        // this.date.getHours(),
        // this.date.getMinutes(), this.date.getSeconds());
        // ZonedDateTime zonedDateTime =
        // localDateTime.atZone(ZoneId.of(desiredTimeZone));
        // System.out.println(Date.from(zonedDateTime.toInstant()));
        // return Date.from(zonedDateTime.toInstant());
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Message() {
    }

    public Message(int id_utilisateur, String contenu) {
        this.id_utilisateur = id_utilisateur;
        this.contenu = contenu;
    }
}
