package cloud.voiture.model.messagerie;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("messagerie")
public class Messagerie {
    @Id
    private String id;
    private Participant[] participants;
    private Message[] messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Participant[] getParticipants() {
        return participants;
    }

    public void setParticipants(Participant[] participants) {
        this.participants = participants;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public Messagerie() {
    }

    public Messagerie(String id, Participant[] participants, Message[] messages) {
        this.id = id;
        this.participants = participants;
        this.messages = messages;
    }
}
