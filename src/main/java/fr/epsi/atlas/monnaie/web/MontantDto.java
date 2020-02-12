package fr.epsi.atlas.monnaie.web;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class MontantDto {
	
	@NotNull(message = "Le montant est obligatoire")
	private BigDecimal montant;
	
	public MontantDto() {
	}

	public MontantDto(BigDecimal montant) {
		this.montant = montant;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
}
