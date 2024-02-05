package cloud.voiture.model.request;

import java.util.Date;

public class MessageReq {
    int idSender;
    int idReceiver;
    String contenu;
    Date dateMessage;

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public MessageReq() {
    }

    public MessageReq(int idSender, int idReceiver, String contenu, Date dateMessage) {
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.contenu = contenu;
        this.dateMessage = dateMessage;
    }
}
