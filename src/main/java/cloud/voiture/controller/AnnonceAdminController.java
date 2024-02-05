package cloud.voiture.controller;

import cloud.voiture.service.AnnonceService;
import cloud.voiture.model.Annonce;
import cloud.voiture.model.ResponseWrap;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/annonce")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnonceAdminController {

    @Autowired
    private AnnonceService annonceService;

    @GetMapping("/toutes")
    public ResponseWrap<List<Annonce>> getAllAnnonce() {
        return ResponseWrap.success(annonceService.getAllAnnonce());
    }

    @GetMapping("/{id}")
    public ResponseWrap<Annonce> getAnnonceById(@PathVariable String id) {
        Optional<Annonce> rep = annonceService.getAnnonceById(id);
        if(rep.isPresent()){
            return ResponseWrap.success(rep.get());
        }
        return ResponseWrap.error("id not found");
    }

    @GetMapping("/non-valider")
    public ResponseWrap<List<Annonce>> getAllAnnonceNonValidee() {
        return ResponseWrap.success(annonceService.getAnnonceNonValidee());
    }

    @PutMapping("/{id}/validation")
    public ResponseWrap<Annonce> valider(@PathVariable String id) {
        try {
            return ResponseWrap.success(annonceService.validerAnnonce(id));
        } catch (Exception e) {
            return ResponseWrap.error(e.getMessage());
        }
    }

    @PutMapping("/{id}/refus")
    public ResponseWrap<Annonce> refuser(@PathVariable String id) {
        try {
            return ResponseWrap.success(annonceService.refuserAnnonce(id));
        } catch (Exception e) {
            return ResponseWrap.error(e.getMessage());
        }
    }

}
