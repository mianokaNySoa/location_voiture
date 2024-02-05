package cloud.voiture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.voiture.model.Pays;

public interface PaysRepository extends JpaRepository<Pays,Integer>{
    List<Pays> findByEtat(int etat);
}
