package fr.epsi.atlas.monnaie.web;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TauxDeChangeDto {
	
	@NotNull(message = "Le taux de change doit être positif")
	@Min(value = 0, message = "Le taux de change doit être positif")
	private BigDecimal tauxDeChange;

	public BigDecimal getTauxDeChange() {
		return tauxDeChange;
	}

	public void setTauxDeChange(BigDecimal tauxDeChange) {
		this.tauxDeChange = tauxDeChange;
	}
}
