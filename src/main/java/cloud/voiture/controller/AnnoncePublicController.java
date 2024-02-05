package cloud.voiture.controller;

import cloud.voiture.service.AnnonceFavorisService;
import cloud.voiture.service.AnnonceService;
import cloud.voiture.model.Annonce;
import cloud.voiture.model.AnnonceFavoris;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.model.request.FiltreAnnonceReq;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/annonce")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnoncePublicController {

    @Autowired
    private AnnonceService annonceService;

    // mahita annonce rehetra na tsy connecter aza
    @GetMapping("accueil")
    public ResponseEntity<ResponseWrap> getAccueil() {
        return ResponseEntity.ok(ResponseWrap.success(annonceService.getAnnoncesValidee()));
    }

    @GetMapping("filtre")
    public List<Annonce> filtreAnnonce(@RequestBody FiltreAnnonceReq filtreAnnonceReq) {
        return annonceService.filtrerAnnonces(filtreAnnonceReq);
    }
}
