package br.com.khadije.zein.starwarsapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.khadije.zein.starwarsapi.domain.dto.PlanetaDTO;
import br.com.khadije.zein.starwarsapi.exception.ApiExceptionHandler;
import br.com.khadije.zein.starwarsapi.service.PlanetaServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class PlanetaControllerTest {

	private MockMvc mockMvc;

	@Mock
	private PlanetaServiceImpl service;

	@InjectMocks
	private PlanetaControllerImpl controller;

	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ApiExceptionHandler()).build();
	}

	private ResultActions doPost(String nome, String clima, String terreno) throws Exception {
		return this.mockMvc
				.perform(post("/planetas").header("nome", nome).header("clima", clima).header("terreno", terreno))
				.andDo(print());

	}

	private ResultActions doGet() throws Exception {
		return this.mockMvc.perform(get("/planetas")).andDo(print());

	}

	private ResultActions doGetId(String id) throws Exception {
		return this.mockMvc.perform(get("/planetas/{id}", id)).andDo(print());

	}

	private ResultActions doGetNome(String nome) throws Exception {
		return this.mockMvc.perform(get("/planetas").param("nome", nome)).andDo(print());

	}

	private ResultActions doDelete(String id) throws Exception {
		return this.mockMvc.perform(delete("/planetas/{id}", id)).andDo(print());

	}

	@Test
	public void adicionarPlanetaNomeVazio() throws Exception {
		String nome = "";
		String clima = "seco";
		String terreno = "arenoso";
		when(service.adicionarPlaneta(any(String.class), any(String.class), any(String.class))).thenReturn(false);

		doPost(nome, clima, terreno).andExpect(status().isPreconditionFailed());
	}

	@Test
	public void adicionarPlanetaClimaVazio() throws Exception {
		String nome = "Tatooine";
		String clima = "";
		String terreno = "arenoso";
		when(service.adicionarPlaneta(any(String.class), any(String.class), any(String.class))).thenReturn(false);

		doPost(nome, clima, terreno).andExpect(status().isPreconditionFailed());
	}

	@Test
	public void adicionarPlanetaTerrenoVazio() throws Exception {
		String nome = "Tatooine";
		String clima = "seco";
		String terreno = "";
		when(service.adicionarPlaneta(any(String.class), any(String.class), any(String.class))).thenReturn(false);

		doPost(nome, clima, terreno).andExpect(status().isPreconditionFailed());
	}

	@Test
	public void adicionarPlanetaCamposVazios() throws Exception {
		String nome = "";
		String clima = "";
		String terreno = "";
		when(service.adicionarPlaneta(any(String.class), any(String.class), any(String.class))).thenReturn(false);

		doPost(nome, clima, terreno).andExpect(status().isPreconditionFailed());
	}

	@Test
	public void adicionarPlanetaCamposValidos() throws Exception {
		String nome = "Tatooine";
		String clima = "seco";
		String terreno = "arenoso";
		String resultado = "Planeta adicionado com sucesso";
		when(service.adicionarPlaneta(any(String.class), any(String.class), any(String.class))).thenReturn(true);

		doPost(nome, clima, terreno).andExpect(status().isOk())
				.andExpect(jsonPath("$.planetaAdicionado").value(resultado));

	}

	@Test
	public void listarPlanetasResultadoValido() throws Exception {

		PlanetaDTO dto = new PlanetaDTO();
		dto.setNome("Tatooine");
		dto.setClima("seco");
		dto.setTerreno("arenoso");
		dto.setQtdeFilmes(5L);
		List<PlanetaDTO> planetas = new ArrayList<>();
		planetas.add(dto);
		when(service.listarPlanetas()).thenReturn(planetas);

		doGet().andExpect(status().isOk()).andExpect(jsonPath("$.[*]").isArray())
				.andExpect(jsonPath("$.[*].length()").value(Matchers.hasSize(1)))
				.andExpect(jsonPath("$.[*].nome").value("Tatooine")).andExpect(jsonPath("$.[*].clima").value("seco"))
				.andExpect(jsonPath("$.[*].terreno").value("arenoso")).andExpect(jsonPath("$.[*].qtdeFilmes").value(5));

	}

	@Test
	public void listarPlanetasResultadoVazio() throws Exception {
		when(service.listarPlanetas()).thenReturn(null);

		doGet().andExpect(status().isNoContent());
	}

	@Test
	public void pesquisarPlanetaIdResultadoVazio() throws Exception {
		String id = "1";
		when(service.pesquisarPlanetaId(id)).thenReturn(null);

		doGetId(id).andExpect(status().isNoContent());
	}

	@Test
	public void pesquisarPlanetaIdResultadoValido() throws Exception {
		String id = "1";
		PlanetaDTO dto = new PlanetaDTO();
		dto.setNome("Tatooine");
		dto.setClima("seco");
		dto.setTerreno("arenoso");
		dto.setQtdeFilmes(5L);
		dto.setId(id);
		when(service.pesquisarPlanetaId(id)).thenReturn(dto);

		doGetId(id).andExpect(status().isOk()).andExpect(jsonPath("$.nome").value("Tatooine"))
				.andExpect(jsonPath("$.clima").value("seco")).andExpect(jsonPath("$.terreno").value("arenoso"))
				.andExpect(jsonPath("$.qtdeFilmes").value(5)).andExpect(jsonPath("$.id").value("1"));
	}

	@Test
	public void pesquisarPlanetaNomeResultadoVazio() throws Exception {
		String nome = "Lol";
		when(service.pesquisarPlanetaNome(nome)).thenReturn(null);

		doGetNome(nome).andExpect(status().isNoContent());
	}

	@Test
	public void pesquisarPlanetaNomeVazio() throws Exception {
		String nome = "";
		when(service.pesquisarPlanetaNome(nome)).thenReturn(null);

		doGetNome(nome).andExpect(status().isPreconditionFailed());
	}

	@Test
	public void pesquisarPlanetaNomeResultadoValido() throws Exception {
		String nome = "Tatooine";
		PlanetaDTO dto = new PlanetaDTO();
		dto.setNome(nome);
		dto.setClima("seco");
		dto.setTerreno("arenoso");
		dto.setQtdeFilmes(5L);
		dto.setId("1");
		List<PlanetaDTO> planetas = new ArrayList<>();
		planetas.add(dto);
		when(service.pesquisarPlanetaNome(nome)).thenReturn(planetas);

		doGetNome(nome).andExpect(status().isOk()).andExpect(jsonPath("$.[*]").isArray())
				.andExpect(jsonPath("$.[*].length()").value(Matchers.hasSize(1)))
				.andExpect(jsonPath("$.[*].nome").value("Tatooine")).andExpect(jsonPath("$.[*].clima").value("seco"))
				.andExpect(jsonPath("$.[*].terreno").value("arenoso")).andExpect(jsonPath("$.[*].qtdeFilmes").value(5))
				.andExpect(jsonPath("$.[*].id").value("1"));
	}

	@Test
	public void removerPlanetaSucesso() throws Exception{
		String id="1";
		String resultado = "Planeta removido com sucesso";
		when(service.removerPlaneta(id))
		.thenReturn(true);

		doDelete(id)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.planetaRemovido").value(resultado));
	}
	
	@Test
	public void removerPlanetaErro() throws Exception {
		String id = "1";
		String resultado = "Erro inesperado ao remover planeta";
		when(service.removerPlaneta(id)).thenReturn(false);

		doDelete(id).andExpect(status().is5xxServerError()).andExpect(jsonPath("$.message").value(resultado));

	}
	
	@Test
	public void removerPlanetaInexistente() throws Exception {
		String id = "1";
		String resultado = "Planeta inexistente";
		when(service.removerPlaneta(id)).thenReturn(null);

		doDelete(id).andExpect(status().isNoContent()).andExpect(jsonPath("$.mensagem").value(resultado));

	}

}