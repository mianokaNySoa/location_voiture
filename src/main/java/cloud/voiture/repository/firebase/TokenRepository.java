package cloud.voiture.repository.firebase;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import cloud.voiture.model.firebase.Token;

public interface TokenRepository extends MongoRepository<Token, String> {
    Token findByIdUtilisateur(int idUtilisateur);
}