package cloud.voiture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cloud.voiture.model.Commission;

public interface CommissionRepository extends JpaRepository<Commission,Long> {
    @Query("SELECT c FROM Commission c WHERE :nb BETWEEN c.min AND c.max ORDER BY c.dates DESC")
    Commission findCommissionByValueAndMaxDate(@Param("nb") double nb);
}
