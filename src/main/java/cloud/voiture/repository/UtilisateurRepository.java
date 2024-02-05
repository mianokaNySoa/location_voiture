package cloud.voiture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cloud.voiture.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
    public Utilisateur findByEmail(@Param("email") String email);
    public List<Utilisateur> findByActif(int actif);
    public long countByActifAndIsadmin(int actif, int isAdmin);
    public List<Utilisateur> findByIsadminAndActifAndIdNot(int isAdmin,int actif,int id);
}
