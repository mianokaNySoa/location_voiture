package cloud.voiture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.voiture.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
    List<Categorie> findByEtat(int etat);
}
