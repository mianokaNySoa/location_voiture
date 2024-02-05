package cloud.voiture.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cloud.voiture.model.messagerie.Messagerie;

public interface MessagerieRepository extends MongoRepository<Messagerie, String> {

}
