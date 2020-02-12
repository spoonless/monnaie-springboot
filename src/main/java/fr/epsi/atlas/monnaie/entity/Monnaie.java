package fr.epsi.atlas.monnaie.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Monnaie {

	/**
	 * le code ISO-4217
	 */
	@Id
	@Column(length = 3)
	@NotNull(message = "Le code monnaie est obligatoire et doit contenir trois lettres")
	@Size(min = 3, max = 3, message = "Le code monnaie est obligatoire et doit contenir trois lettres")
	private String code;

	@NotNull(message = "Le taux de change doit être positif")
	@Min(value = 0, message = "Le taux de change doit être positif")
	@Column(columnDefinition = "decimal(10,5)")
	private BigDecimal tauxDeChange;
	
	public Monnaie() {
	}
	
	public Monnaie(String code, BigDecimal tauxDeChange) {
		this.code = code;
		this.tauxDeChange = tauxDeChange;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getTauxDeChange() {
		return tauxDeChange;
	}
	public void setTauxDeChange(BigDecimal tauxDeChange) {
		this.tauxDeChange = tauxDeChange;
	}

	public BigDecimal convert(BigDecimal montant) {
		return this.tauxDeChange.multiply(montant);
	}
}
