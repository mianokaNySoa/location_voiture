package cloud.voiture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.NombreUser;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.service.StatistiqueService;
import cloud.voiture.model.StatistiqueCommission;
import cloud.voiture.model.StatistiqueNombreVente;

import java.util.List;

@RestController
@RequestMapping("/statistique")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatistiqueController {
    
    @Autowired
    private StatistiqueService statistiqueService;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/commission")
    public ResponseWrap<List<StatistiqueCommission>> getStatistiqueCommission() {
        return ResponseWrap.success(statistiqueService.getAllStatistiqueCommission());
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/vente")
    public ResponseWrap<List<StatistiqueNombreVente>> getStatistiqueVente() {
        return ResponseWrap.success(statistiqueService.getAllStatistiqueVente());
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/nombre_utilisateur")
    public ResponseWrap<NombreUser> getNombre() {
        return ResponseWrap.success(statistiqueService.getUtilisateurActif());
    }
}
