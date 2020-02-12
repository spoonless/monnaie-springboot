package fr.epsi.atlas.monnaie.web;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fr.epsi.atlas.monnaie.entity.Monnaie;
import fr.epsi.atlas.monnaie.service.MonnaieDejaExistanteException;
import fr.epsi.atlas.monnaie.service.MonnaieInexistanteException;
import fr.epsi.atlas.monnaie.service.MonnaieService;

@RestController
@RequestMapping("/monnaie")
public class MonnaieControleur {
	@Autowired
	private MonnaieService monnaieService;
	
	@ExceptionHandler(MonnaieInexistanteException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleException(MonnaieInexistanteException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(MonnaieDejaExistanteException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String handleException(MonnaieDejaExistanteException e) {
		return e.getMessage();
	}

	@PostMapping
	public ResponseEntity<MonnaieRepresentation> create(@Validated @RequestBody Monnaie monnaie,
			                                            UriComponentsBuilder uriBuilder) throws MonnaieDejaExistanteException {
		monnaie = monnaieService.create(monnaie);
		URI uri = uriBuilder.path("/monnaie/{codeMonnaie}").buildAndExpand(monnaie.getCode()).toUri();
		return ResponseEntity.created(uri).body(new MonnaieRepresentation(monnaie));
	}
	
	@GetMapping("/{codeMonnaie}")
	public MonnaieRepresentation getByCode(@PathVariable String codeMonnaie) throws MonnaieInexistanteException {
		return new MonnaieRepresentation(monnaieService.getByCode(codeMonnaie));
	}
	
	@DeleteMapping("/{codeMonnaie}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteByCode(@PathVariable String codeMonnaie) {
		monnaieService.deleteByCode(codeMonnaie);
	}
	
	@PutMapping("/{codeMonnaie}")
	public ResponseEntity<MonnaieRepresentation> createOrModifyByCode(@PathVariable String codeMonnaie,
			                                                          @Validated @RequestBody TauxDeChangeDto tauxDeChangeDto,
			                                                          UriComponentsBuilder uriBuilder) throws MonnaieDejaExistanteException {
		try {
			Monnaie monnaie = monnaieService.modify(codeMonnaie, tauxDeChangeDto.getTauxDeChange());
			return ResponseEntity.ok().body(new MonnaieRepresentation(monnaie));

		} catch (MonnaieInexistanteException e) {
			return create(new Monnaie(codeMonnaie, tauxDeChangeDto.getTauxDeChange()), uriBuilder);
		}	
	}
	
	@PostMapping("/{codeMonnaie}/EUR")
	public MontantDto convert(@PathVariable String codeMonnaie,
			                  @Validated @RequestBody MontantDto montantDto,
			                  UriComponentsBuilder uriBuilder) throws MonnaieInexistanteException {
		BigDecimal resultat = monnaieService.convertToEuro(codeMonnaie, montantDto.getMontant());
		return new MontantDto(resultat);
	}
}