package cloud.voiture.service.firebase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import cloud.voiture.model.firebase.Token;
import cloud.voiture.repository.firebase.TokenRepository;

@Service
public class FirebasePublisherService {
    private final FirebaseMessaging fcm;
    @Autowired
    TokenRepository tokenRepository;

    public FirebasePublisherService(FirebaseMessaging fcm) {
        this.fcm = fcm;
    }

    public String postToClient(String message,
            String registrationToken) throws FirebaseMessagingException {
        Message msg = Message.builder()
                .setToken(registrationToken)
                .putData("body", message).setNotification(Notification.builder().setBody(message).build())
                .build();

        String id = fcm.send(msg);
        return id;
    }

    public Token getDevicesOfClient(int idUtilisateur) {
        return tokenRepository.findByIdUtilisateur(idUtilisateur);
    }
}
