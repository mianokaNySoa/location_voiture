package cloud.voiture.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.voiture.model.Annonce;
import cloud.voiture.model.AnnonceFavoris;
import cloud.voiture.repository.AnnonceFavorisRepository;
import cloud.voiture.repository.AnnonceRepository;

@Service
public class AnnonceFavorisService {
    @Autowired
    private AnnonceFavorisRepository annonceFavorisRepository;

    // public List<AnnonceFavoris> getAnnonceFavoris(int idutilisateur){
    //     return annonceFavorisRepository.findByIdutilisateur(idutilisateur);
    // }

    @Autowired
    private AnnonceRepository annonceRepository;

    public List<Annonce> getAnnoncesFavoris(int idutilisateur) {
        // Récupérer la liste des annonces favoris pour l'utilisateur
        List<AnnonceFavoris> annoncesFavoris = annonceFavorisRepository.findByIdutilisateur(idutilisateur);

        // Extraire les IDs d'annonce des favoris
        List<String> annonceIds = annoncesFavoris.stream()
                .map(AnnonceFavoris::getIdannonce)
                .collect(Collectors.toList());

        // Récupérer les annonces correspondantes aux IDs
        return annonceRepository.findByIdIn(annonceIds);
    }

    public AnnonceFavoris saveAnnonceFavoris(String idannonce,int iduser) throws Exception {
        Optional<AnnonceFavoris> existingFavoris = annonceFavorisRepository.findByIdannonceAndIdutilisateur(idannonce, iduser);
        if(existingFavoris.isPresent()){
            throw new Exception("Cette annonce "+idannonce+" est deja en favoris");
        }

        Optional<Annonce> annonce = annonceRepository.findById(idannonce);
        
        if (annonce.isPresent()) {
            AnnonceFavoris annonceFavoris = new AnnonceFavoris();
            annonceFavoris.setIdannonce(idannonce);
            annonceFavoris.setIdutilisateur(iduser);
            return annonceFavorisRepository.save(annonceFavoris);
        } else {
            throw new IllegalArgumentException("Annonce with idannonce " +idannonce + " does not exist.");
        }
    }

    public void deleteAnnonceFavoris(String idannonce, int idutilisateur) {
        Optional<AnnonceFavoris> existingFavoris = annonceFavorisRepository.findByIdannonceAndIdutilisateur(idannonce, idutilisateur);

        if (existingFavoris.isPresent()) {
            annonceFavorisRepository.delete(existingFavoris.get());
        } else {
            throw new IllegalArgumentException("Annonce with idannonce " + idannonce + " is not in the favorites for user with id " + idutilisateur);
        }
    }

    



}
