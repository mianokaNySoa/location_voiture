package cloud.voiture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.voiture.model.Types;

public interface TypesRepository extends JpaRepository<Types,Integer>{
    List<Types> findByEtat(int etat);
}
