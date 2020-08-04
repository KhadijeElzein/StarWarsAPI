package br.com.khadije.zein.starwarsapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.khadije.zein.starwarsapi.domain.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String>{
	public List<Planeta> findByNomeContainsIgnoreCase(String nome);
	public Planeta findByNomeIgnoreCaseAndTerrenoIgnoreCaseAndClimaIgnoreCase(String nome, String terreno, String clima);

}
