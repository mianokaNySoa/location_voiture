package cloud.voiture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.voiture.model.Carburant;

public interface CarburantRepository extends JpaRepository<Carburant,Long>{
    
}
