package cloud.voiture.service.firebase;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.voiture.model.firebase.Token;
import cloud.voiture.repository.firebase.TokenRepository;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Token createOrUpdateToken(Token token) {
        token.setTimestamp(LocalDateTime.now());
        return tokenRepository.save(token);
    }

    public Token getTokenById(String tokenId) {
        return tokenRepository.findById(tokenId).orElse(null);
    }

    public void deleteToken(String tokenId) {
        tokenRepository.deleteById(tokenId);
    }
}
