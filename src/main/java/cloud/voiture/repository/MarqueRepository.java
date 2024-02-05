package cloud.voiture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.voiture.model.Marque;

public interface MarqueRepository extends JpaRepository<Marque,Integer>{
    List<Marque> findByEtat(int etat);
}
