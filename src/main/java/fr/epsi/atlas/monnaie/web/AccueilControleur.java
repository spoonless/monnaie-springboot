package fr.epsi.atlas.monnaie.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccueilControleur {

	@GetMapping("/")
	public String accueil() {
		return "Bienvenue sur le site de l'API Web de gestion de la monnaie.";
	}
	
}
