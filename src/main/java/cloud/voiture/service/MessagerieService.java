package cloud.voiture.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import cloud.voiture.model.Utilisateur;
import cloud.voiture.model.messagerie.Message;
import cloud.voiture.model.messagerie.Messagerie;
import cloud.voiture.model.messagerie.Participant;
import cloud.voiture.repository.UtilisateurRepository;

@Service
public class MessagerieService {
    private final MongoTemplate mongoTemplate;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public MessagerieService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Messagerie> getMessageries(int idUtilisateur) {
        return this.mongoTemplate.find(
                Query.query(Criteria.where("participants.id_utilisateur").in(
                        idUtilisateur)),
                Messagerie.class);
    }

    public void ajouterMessage(int idSender, int idReceiver, String contenu, Date dateMessage) throws Exception {
        Messagerie messagerie = trouverMessagerie(idSender, idReceiver);
        if (messagerie == null) {
            creerNouvelleMessagerie(idSender, idReceiver, contenu, dateMessage);
            return;
        }
        Message nouveauMessage = new Message();
        nouveauMessage.setId_utilisateur(idSender);
        nouveauMessage.setContenu(contenu);
        nouveauMessage.setDate(dateMessage);
        this.mongoTemplate.updateFirst(
                Query.query(Criteria.where("participants.id_utilisateur").all(
                        idSender, idReceiver)),
                new Update().push("messages", nouveauMessage),
                Messagerie.class);
    }

    private void creerNouvelleMessagerie(int idSender, int idReceiver, String contenu, Date dateMessage)
            throws Exception {
        Messagerie nouvelleMessagerie = new Messagerie();
        Optional<Utilisateur> sender = this.utilisateurRepository.findById(idSender);
        Optional<Utilisateur> receiver = this.utilisateurRepository.findById(idReceiver);
        try {
            Participant participant1 = new Participant(sender.get().getId(),
                    sender.get().getNom() + " " + sender.get().getPrenom());
            Participant participant2 = new Participant(receiver.get().getId(),
                    receiver.get().getNom() + " " + sender.get().getPrenom());

            nouvelleMessagerie.setParticipants(Arrays.asList(participant1, participant2).toArray(new Participant[0]));

            Message nouveauMessage = new Message();
            nouveauMessage.setId_utilisateur(idSender);
            nouveauMessage.setContenu(contenu);
            nouveauMessage.setDate(dateMessage);

            nouvelleMessagerie.setMessages(Collections.singletonList(nouveauMessage).toArray(new Message[0]));

            this.mongoTemplate.save(nouvelleMessagerie);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("L'un des utilisateurs n'existe pas");
        }
    }

    private Messagerie trouverMessagerie(int idSender, int idReceiver) {
        return this.mongoTemplate.findOne(
                Query.query(Criteria.where("participants.id_utilisateur").all(
                        idSender, idReceiver)),
                Messagerie.class);
    }

    public void supprimerMessage(int idSender, int idReceiver, Date dateMessage, String contenuMessage) {
        Messagerie messagerie = trouverMessagerie(idSender, idReceiver);

        if (messagerie != null) {
            Query query = new Query();
            query.addCriteria(Criteria.where("participants.id_utilisateur").all(
                    idSender, idReceiver));
            query.addCriteria(Criteria.where("messages.id_utilisateur").is(idSender)
                    .and("messages.date").is(dateMessage)
                    .and("messages.contenu").is(contenuMessage));

            Update update = new Update().pull("messages",
                    Query.query(Criteria.where("id_utilisateur").is(idSender)
                            .and("date").is(dateMessage)
                            .and("contenu").is(contenuMessage)));

            this.mongoTemplate.updateFirst(query, update, Messagerie.class);
        }
    }
}
