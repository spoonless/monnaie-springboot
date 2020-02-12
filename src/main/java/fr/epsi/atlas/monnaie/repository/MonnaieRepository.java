package fr.epsi.atlas.monnaie.repository;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.atlas.monnaie.entity.Monnaie;

public interface MonnaieRepository extends CrudRepository<Monnaie, String>{

}
