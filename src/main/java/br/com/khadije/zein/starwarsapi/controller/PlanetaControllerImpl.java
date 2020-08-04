package br.com.khadije.zein.starwarsapi.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.khadije.zein.starwarsapi.controller.interfaces.PlanetaController;
import br.com.khadije.zein.starwarsapi.domain.dto.PlanetaDTO;
import br.com.khadije.zein.starwarsapi.exception.NoContentException;
import br.com.khadije.zein.starwarsapi.exception.RequestException;
import br.com.khadije.zein.starwarsapi.service.interfaces.PlanetaService;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

@RestController
public class PlanetaControllerImpl implements PlanetaController {

	private static final Logger logger = LoggerFactory.getLogger(PlanetaControllerImpl.class);
	@Autowired
	private PlanetaService service;

	@Override
	public ResponseEntity<?> adicionarPlaneta(String nome, String clima, String terreno) throws Exception {
		logger.info("Recebendo dados para adicionar planeta. Nome: {} - Clima: {}, Terreno: {}", nome, clima, terreno);
		Boolean response = null;
		if (StringUtils.isBlank(nome) || StringUtils.isBlank(clima) || StringUtils.isBlank(terreno))
			throw new IllegalArgumentException();
		else {
			response = service.adicionarPlaneta(nome, clima, terreno);
			if (response)
				return ResponseEntity.ok()
						.body(Collections.singletonMap("planetaAdicionado", "Planeta adicionado com sucesso"));
			else if (!response)
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new RequestException(System.currentTimeMillis(),
								HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), "Bad request",
								"Erro inesperado ao adicionar planeta", PlanetaController.PATH));
		}
		logger.info("Finalizada adição de planeta. Nome: {} - Clima: {}, Terreno: {}", nome, clima, terreno);
		throw new NoContentException();
	}

	@Override
	public ResponseEntity<?> listarPlanetas() {
		logger.info("Recebendo listagem de planetas.");
		List<PlanetaDTO> planetas = service.listarPlanetas();
		if (planetas == null)
			throw new NoContentException();
		logger.info("Finalizada listagem de planeta");
		return ResponseEntity.ok().body(planetas);
	}

	@Override
	public ResponseEntity<?> pesquisarPlanetaId(String id) {
		logger.info("Recebendo consulta de planeta por id. Id: {}",id);
		if (StringUtils.isBlank(id))
			throw new IllegalArgumentException();
		else {
			PlanetaDTO planeta = service.pesquisarPlanetaId(id);
			if (planeta == null)
				throw new NoContentException();
			logger.info("Finalizada consulta de planeta por id");
			return ResponseEntity.ok().body(planeta);
		}
	}

	@Override
	public ResponseEntity<?> pesquisarPlanetaNome(String nome) {
		logger.info("Recebendo consulta de planeta por nome. Nome: {}",nome);

		if (StringUtils.isBlank(nome))
			throw new IllegalArgumentException();
		else {
			List<PlanetaDTO> planetas = service.pesquisarPlanetaNome(nome);
			if (planetas == null)
				throw new NoContentException();	
			logger.info("Finalizada consulta de planeta por nome");
			return ResponseEntity.ok().body(planetas);
		}
	}

	@Override
	public ResponseEntity<?> removerPlaneta(String id) {
		logger.info("Recebendo dados para remoção de planeta. Id: {}",id);
		Boolean response = service.removerPlaneta(id);
		if(response == null) throw new NoContentException("Planeta inexistente");
		if (response)
			return ResponseEntity.ok()
					.body(Collections.singletonMap("planetaRemovido", "Planeta removido com sucesso"));
		if(!response)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new RequestException(System.currentTimeMillis(),
							HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), "Bad request",
							"Erro inesperado ao remover planeta", PlanetaController.PATH));
		logger.info("Finalizada remoção de planeta");
		throw new NoContentException("Planeta inexistente");
	}
}