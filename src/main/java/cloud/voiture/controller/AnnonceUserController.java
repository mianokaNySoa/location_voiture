package cloud.voiture.controller;

import cloud.voiture.service.AnnonceFavorisService;
import cloud.voiture.service.AnnonceService;
import jakarta.servlet.http.HttpServletRequest;
import cloud.voiture.authentification.JwtUtil;
import cloud.voiture.model.Annonce;
import cloud.voiture.model.AnnonceFavoris;
import cloud.voiture.model.AnnoncePostRequest;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.model.Utilisateur;
import cloud.voiture.repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/annonce")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnonceUserController {

    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private AnnonceFavorisService annonceFavorisService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @GetMapping("accueil")
    public ResponseEntity<ResponseWrap> getAccueil(HttpServletRequest request) {
        try {
            int iduser = annonceService.getIdUtilisateurFromJwt(request);

            List<Annonce> annonces = annonceService.getAccueilConnectee(iduser);
            return ResponseEntity.ok(ResponseWrap.success(annonces));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la récupération des annonces : " + e.getMessage()));
        }
    }

    @GetMapping("mesannonces")
    public ResponseEntity<ResponseWrap> getMesAnnonces(HttpServletRequest request) {
        try {
            int iduser = annonceService.getIdUtilisateurFromJwt(request);

            List<Annonce> annonces = annonceService.getMesAnnonces(iduser);
            return ResponseEntity.ok(ResponseWrap.success(annonces));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la récupération des annonces : " + e.getMessage()));
        }
    }

    
    @PostMapping
    public ResponseEntity<ResponseWrap> createAnnonce(@RequestBody AnnoncePostRequest annoncePost,HttpServletRequest request) {
        try {
            if(annoncePost.getAnnonce().getUtilisateur() == null){
                int iduser = annonceService.getIdUtilisateurFromJwt(request);
                Optional<Utilisateur> utilisateur = utilisateurRepository.findById(iduser);
                annoncePost.getAnnonce().setUtilisateur(utilisateur.get());
                
            }
            Annonce savedAnnonce = annonceService.saveAnnonce(annoncePost.getAnnonce(),annoncePost.getImage());
            return ResponseEntity.ok(ResponseWrap.success(savedAnnonce));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseWrap.error(e.getMessage()));
        }
        
    }


    /*
     * ANNONCE FAVORIS
     * 
     */
   
    @GetMapping("/favoris")
    public ResponseEntity<ResponseWrap> getMesAnnoncesFavoris(HttpServletRequest request) {
        try {
            int iduser = annonceService.getIdUtilisateurFromJwt(request);

            List<Annonce> annonces = annonceFavorisService.getAnnoncesFavoris(iduser);
            return ResponseEntity.ok(ResponseWrap.success(annonces));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la récupération des annonces : " + e.getMessage()));
        }
    }

    @PostMapping("/favoris/ajouter/{idannonce}")
    public ResponseEntity<ResponseWrap> saveAnnonceFavoris(@PathVariable String idannonce,HttpServletRequest request) {
        try {
            int iduser = annonceService.getIdUtilisateurFromJwt(request);

            AnnonceFavoris savedAnnonceFavoris = annonceFavorisService.saveAnnonceFavoris(idannonce,iduser);
            return ResponseEntity.ok(ResponseWrap.success(savedAnnonceFavoris));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la sauvegarde de l'annonce favoris : " + e.getMessage()));
        }
    }

    @DeleteMapping("/favoris/supprimer/{idannonce}")
    public ResponseEntity<ResponseWrap> deleteAnnonceFavoris(@PathVariable String idannonce,HttpServletRequest request) {
        try {
            int iduser = annonceService.getIdUtilisateurFromJwt(request);

            annonceFavorisService.deleteAnnonceFavoris(idannonce,iduser);
            return ResponseEntity.ok(ResponseWrap.success("supprimer du favoris avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la suppression de l'annonce favoris : " + e.getMessage()));
        }
    }

    @PostMapping("/vendre/{annonceId}/acheteur/{acheteurId}")
    public ResponseEntity<ResponseWrap> changerStatusToSold(@PathVariable String annonceId,@PathVariable int acheteurId,HttpServletRequest request) {
        try {
            int vendeurId = annonceService.getIdUtilisateurFromJwt(request);

            Annonce vendu = annonceService.changeStatusToSold(vendeurId, acheteurId, annonceId);
            return ResponseEntity.ok(ResponseWrap.success(vendu));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error(e.getMessage()));
        }
    }

    @DeleteMapping("/supprimer/{annonceId}")
    public ResponseEntity<ResponseWrap> supprmerMonAnnonce(@PathVariable String annonceId,HttpServletRequest request) {
        try {
            int iduser = annonceService.getIdUtilisateurFromJwt(request);

            annonceService.supprimerMonAnnonce(iduser, annonceId);
            return ResponseEntity.ok(ResponseWrap.success("annonce supprimer avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la supperssion de l'annonce  : " + e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseWrap<Annonce> getAnnonceById(@PathVariable String id) {
        Optional<Annonce> rep = annonceService.getAnnonceById(id);
        if(rep.isPresent()){
            return ResponseWrap.success(rep.get());
        }
        return ResponseWrap.error("id not found");
    }

}
