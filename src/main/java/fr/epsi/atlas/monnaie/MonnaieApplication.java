package fr.epsi.atlas.monnaie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"fr.epsi.atlas.monnaie.web", "fr.epsi.atlas.monnaie.service"})
@EntityScan("fr.epsi.atlas.monnaie.entity")
@EnableJpaRepositories("fr.epsi.atlas.monnaie.repository")
public class MonnaieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonnaieApplication.class, args);
	}

}
