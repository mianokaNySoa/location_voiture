package cloud.voiture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.voiture.model.Historique;

public interface HistoriqueRepository extends JpaRepository<Historique,Long> {
    
}
