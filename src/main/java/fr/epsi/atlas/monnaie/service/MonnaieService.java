package fr.epsi.atlas.monnaie.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.atlas.monnaie.entity.Monnaie;
import fr.epsi.atlas.monnaie.repository.MonnaieRepository;

@Service
public class MonnaieService {
	
	private MonnaieRepository monnaieRepository;
	
	public MonnaieService(@Autowired MonnaieRepository monnaieRepository) {
		this.monnaieRepository = monnaieRepository;
	}

	public Monnaie getByCode(String codeMonnaie) throws MonnaieInexistanteException {
		Optional<Monnaie> optionalMonnaie = monnaieRepository.findById(codeMonnaie);
		if(! optionalMonnaie.isPresent()) {
			throw new MonnaieInexistanteException("La monnaie avec le code " + codeMonnaie + " n'existe pas.");
		}
		return optionalMonnaie.get();
	}

	@Transactional
	public void deleteByCode(String codeMonnaie) {
		if (monnaieRepository.existsById(codeMonnaie)) {
			monnaieRepository.deleteById(codeMonnaie);
		}
	}

	@Transactional
	public Monnaie modify(String codeMonnaie, BigDecimal tauxDeChange) throws MonnaieInexistanteException {
		Monnaie monnaie = getByCode(codeMonnaie);
		monnaie.setTauxDeChange(tauxDeChange);
		return monnaie;
	}

	@Transactional
	public Monnaie create(Monnaie monnaie) throws MonnaieDejaExistanteException {
		if (monnaieRepository.existsById(monnaie.getCode())) {
			throw new MonnaieDejaExistanteException("La monnaie avec le code " + monnaie.getCode() + " existe déjà");
		}
		return monnaieRepository.save(monnaie);
	}

	public BigDecimal convertToEuro(String codeMonnaie, BigDecimal montant) throws MonnaieInexistanteException {
		Monnaie monnaie = getByCode(codeMonnaie);
		return monnaie.convert(montant);
	}

}