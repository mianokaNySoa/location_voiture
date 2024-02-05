package cloud.voiture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.voiture.repository.StatistiqueCommissionRepository;
import cloud.voiture.repository.StatistiqueNombreVenteRepository;
import cloud.voiture.repository.UtilisateurRepository;
import cloud.voiture.model.NombreUser;
import cloud.voiture.model.StatistiqueCommission;
import cloud.voiture.model.StatistiqueNombreVente;

@Service
public class StatistiqueService {
    @Autowired
    private StatistiqueCommissionRepository statistiqueCommissionRepository;

    @Autowired
    private StatistiqueNombreVenteRepository statistiqueNombreVenteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<StatistiqueCommission> getAllStatistiqueCommission(){
        return statistiqueCommissionRepository.findAll();
    }

    public List<StatistiqueNombreVente> getAllStatistiqueVente(){
        return statistiqueNombreVenteRepository.findAll();
    }

    public NombreUser getUtilisateurActif(){
        return new NombreUser(utilisateurRepository.countByActifAndIsadmin(1, 1), utilisateurRepository.countByActifAndIsadmin(1, 0));
    }

}
