package cloud.voiture.controller.firebase;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.firebase.Token;
import cloud.voiture.model.response.ErrorRes;
import cloud.voiture.service.AnnonceService;
import cloud.voiture.service.firebase.TokenService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PushNotificationController {
    @Autowired
    AnnonceService annonceService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/registerDeviceToken")
    public ResponseEntity registerDeviceToken(@RequestBody String deviceToken, HttpServletRequest request) {
        try {
            System.out.println("Received device token: " + deviceToken);
            Token tk = new Token(annonceService.getIdUtilisateurFromJwt(request), deviceToken, LocalDateTime.now());
            return ResponseEntity.ok(tokenService.createOrUpdateToken(tk));
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
