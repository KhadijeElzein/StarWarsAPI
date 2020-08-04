package br.com.khadije.zein.starwarsapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.khadije.zein.starwarsapi.domain.Planeta;
import br.com.khadije.zein.starwarsapi.domain.RespostaSwapi;
import br.com.khadije.zein.starwarsapi.domain.ResultadosSwapi;
import br.com.khadije.zein.starwarsapi.domain.dto.PlanetaDTO;
import br.com.khadije.zein.starwarsapi.exception.BusinessException;
import br.com.khadije.zein.starwarsapi.repository.PlanetaRepository;
import br.com.khadije.zein.starwarsapi.service.interfaces.PlanetaService;
import br.com.khadije.zein.starwarsapi.utils.MethodUtils;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;


@Service
public class PlanetaServiceImpl implements PlanetaService{

	@Autowired
    private PlanetaRepository repository;
	
	private String url;
	
	private MethodUtils methodUtils;
	
	private RespostaSwapi resposta;
	
	public PlanetaServiceImpl() {
		this.url = null;
		this.methodUtils = new MethodUtils();
		this.resposta = null;
	}

	@Override
	public Boolean adicionarPlaneta(String nome, String clima, String terreno) throws BusinessException {
		if(StringUtils.isBlank(nome) || StringUtils.isBlank(clima) || StringUtils.isBlank(terreno)) return null;
		resposta = methodUtils.callAPI("https://swapi.dev/api/planets/?search="+nome);
		Planeta planeta = new Planeta();
		if(resposta!=null) {
			if(resposta.getResults()
					.stream()
					.filter(resultado -> resultado.getName().equalsIgnoreCase(nome))
					.collect(Collectors.toList()).isEmpty()) {
				throw new BusinessException("planeta não válido no mundo de star wars");
			}	else if(repository.findByNomeIgnoreCaseAndTerrenoIgnoreCaseAndClimaIgnoreCase(nome, terreno, clima) != null) {
				throw new BusinessException("planeta já existente");
			} else {
				planeta.setClima(clima);
				planeta.setNome(nome);
				planeta.setTerreno(terreno);
				planeta = repository.save(planeta);
				return (planeta!=null);
			}
		}
		return null;
	}

	@Override
	public List<PlanetaDTO> listarPlanetas() {
		List<Planeta> planetas = repository.findAll();
		if(planetas == null) return null;
		if(planetas.isEmpty()) return null;
		List<PlanetaDTO> planetasDTO = new ArrayList<>();
		Long qtdeFilmes = 0L;
		for(Planeta planeta:planetas) {
			url = "https://swapi.dev/api/planets/?search="+planeta.getNome();
			resposta = methodUtils.callAPI(url);
			for(ResultadosSwapi resultado : resposta.getResults()) {
			   qtdeFilmes += resultado.getFilms().size();
			}
			planetasDTO.add(new PlanetaDTO(planeta.getId(),planeta.getNome(),planeta.getClima(),planeta.getTerreno(),qtdeFilmes));
		}
		return planetasDTO;
	}

	@Override
	public List<PlanetaDTO> pesquisarPlanetaNome(String nome) {
		List<Planeta> planetas = repository.findByNomeContainsIgnoreCase(nome);
		if(planetas == null) return null;
		if(planetas.isEmpty()) return null;
		List<PlanetaDTO> planetasDTO = new ArrayList<>();
		Long qtdeFilmes = 0L;
		for(Planeta planeta:planetas) {
			url = "https://swapi.dev/api/planets/?search="+planeta.getNome();
			resposta = methodUtils.callAPI(url);
			for(ResultadosSwapi resultado : resposta.getResults()) {
			   qtdeFilmes += resultado.getFilms().size();
			}
			planetasDTO.add(new PlanetaDTO(planeta.getId(),planeta.getNome(),planeta.getClima(),planeta.getTerreno(),qtdeFilmes));
		}
		return planetasDTO;
	}

	@Override
	public PlanetaDTO pesquisarPlanetaId(String id) {
		if(StringUtils.isBlank(id)) return null;
		Optional<Planeta> optional = repository.findById(id);
		Long qtdeFilmes = 0L;
		if(optional == null) return null;
		if(optional.isPresent()) {
			Planeta planeta = optional.get();
			url = "https://swapi.dev/api/planets/?search=" + planeta.getNome();
			resposta = methodUtils.callAPI(url);
			for(ResultadosSwapi resultado : resposta.getResults()) {
			    qtdeFilmes += resultado.getFilms().size();
			}
		    PlanetaDTO planetaDTO = new PlanetaDTO(planeta.getId(),planeta.getNome(),planeta.getClima(),planeta.getTerreno(),qtdeFilmes);	
		    return planetaDTO;
		}
		return null;
	}

	@Override
	public Boolean removerPlaneta(String id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return !repository.existsById(id);
		}
		return null;
	}
}
