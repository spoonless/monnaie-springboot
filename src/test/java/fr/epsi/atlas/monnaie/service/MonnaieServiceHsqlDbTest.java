package fr.epsi.atlas.monnaie.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.epsi.atlas.monnaie.entity.Monnaie;
import fr.epsi.atlas.monnaie.repository.MonnaieRepository;
import fr.epsi.atlas.monnaie.service.MonnaieService;

@SpringBootTest({"spring.datasource.url=jdbc:hsqldb:mem:maBase", 
		         "spring.datasource.driver-class-name=org.hsqldb.jdbcDriver", 
		         "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.HSQLDialect", 
		         "spring.jpa.hibernate.ddl-auto=create"})
public class MonnaieServiceHsqlDbTest {
	
	@Autowired
	private MonnaieService sut;

	@Autowired
	private MonnaieRepository monnaieRepository;
	
	@Test
	public void createMonnaie_sauveLaMonnaieDansLeRepository() throws Exception {
		BigDecimal tauxDeChange = BigDecimal.valueOf(1.23);
		Monnaie monnaie = new Monnaie("USD", tauxDeChange);
		
		sut.create(monnaie);
		
		Optional<Monnaie> resultat = monnaieRepository.findById(("USD"));
		assertTrue(resultat.isPresent());
		assertEquals(1.23, resultat.get().getTauxDeChange().doubleValue(), 0);
	}
}
