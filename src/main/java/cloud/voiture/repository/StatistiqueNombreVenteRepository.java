package cloud.voiture.repository;

import cloud.voiture.model.StatistiqueNombreVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatistiqueNombreVenteRepository extends JpaRepository<StatistiqueNombreVente,Long>{
    
}
