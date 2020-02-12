package fr.epsi.atlas.monnaie.web;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import fr.epsi.atlas.monnaie.entity.Monnaie;

public class MonnaieRepresentation extends RepresentationModel<MonnaieRepresentation> {
	
	private Monnaie monnaie;
	
	public MonnaieRepresentation(Monnaie monnaie) {
		this.monnaie = monnaie;
		add(WebMvcLinkBuilder.linkTo(MonnaieControleur.class).slash(monnaie.getCode()).withSelfRel());
		add(WebMvcLinkBuilder.linkTo(MonnaieControleur.class).slash(monnaie.getCode()).slash("EUR").withRel("http://monnaie.fr/conversion"));
	}

	public String getCode() {
		return monnaie.getCode();
	}

	public BigDecimal getTauxDeChange() {
		return monnaie.getTauxDeChange();
	}

}
