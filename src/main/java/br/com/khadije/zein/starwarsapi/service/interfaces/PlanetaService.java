package br.com.khadije.zein.starwarsapi.service.interfaces;

import java.util.List;

import br.com.khadije.zein.starwarsapi.domain.dto.PlanetaDTO;
import br.com.khadije.zein.starwarsapi.exception.BusinessException;

public interface PlanetaService {
	
	public Boolean adicionarPlaneta(String nome, String clima, String terreno) throws BusinessException;
	
	public List<PlanetaDTO> listarPlanetas();
	
	public List<PlanetaDTO> pesquisarPlanetaNome(String nome);
	
	public PlanetaDTO pesquisarPlanetaId(String id);
	
	public Boolean removerPlaneta(String id);

}
