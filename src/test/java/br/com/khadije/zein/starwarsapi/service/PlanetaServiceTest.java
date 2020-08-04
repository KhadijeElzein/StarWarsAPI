package br.com.khadije.zein.starwarsapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.khadije.zein.starwarsapi.domain.Planeta;
import br.com.khadije.zein.starwarsapi.domain.dto.PlanetaDTO;
import br.com.khadije.zein.starwarsapi.exception.BusinessException;
import br.com.khadije.zein.starwarsapi.repository.PlanetaRepository;


@TestInstance(Lifecycle.PER_CLASS)
class PlanetaServiceTest {

	@Mock
	private PlanetaRepository repository;

	@InjectMocks
	private PlanetaServiceImpl service;

	@BeforeAll
	private void setUpBeforeClass() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void adicionarPlanetaNomeVazio() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setClima("seco");

		planeta.setNome("");
		planeta.setTerreno("arenoso");
		when(repository.save(planeta)).thenReturn(null);
		Boolean result = service.adicionarPlaneta("", "seco", "arenoso");
		assertEquals(null, result);
	}

	@Test
	public void adicionarPlanetaClimaVazio() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setClima("");
		planeta.setNome("Tatooine");
		planeta.setTerreno("arenoso");
		when(repository.save(planeta)).thenReturn(null);

		Boolean result = service.adicionarPlaneta("Tatooine", "", "arenoso");
		assertEquals(null, result);
	}

	@Test
	public void adicionarPlanetaTerrenoVazio() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setClima("seco");
		planeta.setNome("Tatooine");
		planeta.setTerreno("");
		when(repository.save(planeta)).thenReturn(null);

		Boolean result = service.adicionarPlaneta("Tatooine", "seco", "");
		assertEquals(null, result);
	}

	@Test
	public void adicionarPlanetaCamposVazios() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setClima("");
		planeta.setNome("");
		planeta.setTerreno("");
		when(repository.save(planeta)).thenReturn(null);

		Boolean result = service.adicionarPlaneta("", "", "");
		assertEquals(null, result);
	}

	@Test
	public void adicionarPlanetaInvalido() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setClima("seco");
		planeta.setNome("Lol");
		planeta.setTerreno("arenoso");
		when(repository.save(planeta)).thenReturn(null);
		BusinessException ex = assertThrows(BusinessException.class,
				() -> service.adicionarPlaneta("Lol", "seco", "arenoso"));
		String mensagem = "planeta não válido no mundo de star wars";
		assertEquals(ex.getMensagem(), mensagem);
	}

	@Test
	public void adicionarPlanetaDadosValidos() throws Exception {
		Planeta planeta = new Planeta();
		String clima = "seco";
		String nome = "Tatooine";
		String terreno = "arenoso";
		planeta.setClima(clima);
		planeta.setNome(nome);
		planeta.setTerreno(terreno);
		when(repository.save(any(Planeta.class))).thenReturn(planeta);
		Boolean result = service.adicionarPlaneta(nome, clima, terreno);
		assertTrue(result);
	}

	@Test
	public void listarPlanetasSemValores() {
		when(repository.findAll()).thenReturn(null);
		List<PlanetaDTO> result = service.listarPlanetas();
		assertEquals(null, result);
	}

	@Test
	public void listarPlanetasVazio() {
		when(repository.findAll()).thenReturn(new ArrayList<>());
		List<PlanetaDTO> result = service.listarPlanetas();
		assertEquals(null, result);
	}

	@Test
	public void listarPlanetas() {
		List<PlanetaDTO> planetas = new ArrayList<>();
		List<Planeta> plans = new ArrayList<>();
		PlanetaDTO planeta = new PlanetaDTO();
		String clima = "seco";
		String nome = "Tatooine";
		String terreno = "arenoso";
		Long qtde = 5L;
		Planeta plan = new Planeta();
		plan.setClima(clima);
		planeta.setClima(clima);
		plan.setNome(nome);
		planeta.setNome(nome);
		plan.setTerreno(terreno);
		planeta.setTerreno(terreno);
		planeta.setQtdeFilmes(qtde);
		planetas.add(planeta);
		plans.add(plan);
		when(repository.findAll()).thenReturn(plans);
		List<PlanetaDTO> result = service.listarPlanetas();
		assertEquals(result.size(), 1);
		PlanetaDTO resultPlaneta = result.get(0);
		assertEquals(resultPlaneta.getClima(), "seco");
		assertEquals(resultPlaneta.getTerreno(), "arenoso");
		assertEquals(resultPlaneta.getNome(), "Tatooine");
		assertEquals(resultPlaneta.getQtdeFilmes(), 5);
	}

	@Test
	public void pesquisarPlanetaNomeVazio() throws Exception {
		String nome = "";
		when(repository.findByNomeContainsIgnoreCase(nome)).thenReturn(new ArrayList<>());
		List<PlanetaDTO> planetas = service.pesquisarPlanetaNome(nome);
		assertEquals(null, planetas);
	}

	@Test
	public void pesquisarPlanetaNomeResultadoVazio() {
		String nome = "Oregon";
		when(repository.findByNomeContainsIgnoreCase(nome)).thenReturn(new ArrayList<>());
		List<PlanetaDTO> result = service.pesquisarPlanetaNome(nome);
		assertEquals(null, result);
	}

	@Test
	public void pesquisarPlanetaNomeResultadoValido() {
		List<PlanetaDTO> planetas = new ArrayList<>();
		List<Planeta> plans = new ArrayList<>();
		PlanetaDTO planeta = new PlanetaDTO();
		String clima = "seco";
		String nome = "Tatooine";
		String terreno = "arenoso";
		Long qtde = 5L;
		Planeta plan = new Planeta();
		plan.setClima(clima);
		planeta.setClima(clima);
		plan.setNome(nome);
		planeta.setNome(nome);
		plan.setTerreno(terreno);
		planeta.setTerreno(terreno);
		planeta.setQtdeFilmes(qtde);
		planetas.add(planeta);
		plans.add(plan);
		when(repository.findByNomeContainsIgnoreCase(nome)).thenReturn(plans);
		List<PlanetaDTO> result = service.pesquisarPlanetaNome(nome);
		assertEquals(result.size(), 1);
		PlanetaDTO resultPlaneta = result.get(0);
		assertEquals(resultPlaneta.getClima(), "seco");
		assertEquals(resultPlaneta.getTerreno(), "arenoso");
		assertEquals(resultPlaneta.getNome(), "Tatooine");
		assertEquals(resultPlaneta.getQtdeFilmes(), 5);
	}

	@Test
	public void pesquisarPlanetaIdVazio() throws Exception {
		String id = "";
		when(repository.findById(id)).thenReturn(null);
		PlanetaDTO planeta = service.pesquisarPlanetaId(id);
		assertEquals(null, planeta);
	}
	
	@Test
	public void pesquisarPlanetaIdSemValores() {
		String id = "1";
		when(repository.findById(id)).thenReturn(null);
		PlanetaDTO result = service.pesquisarPlanetaId(id);
		assertEquals(null, result);
	}
	
	@Test
	public void pesquisarPlanetaIdResultadoValido() {
		PlanetaDTO planeta = new PlanetaDTO();
		Planeta plan = new Planeta();
		String clima = "seco";
		String nome = "Tatooine";
		String terreno = "arenoso";
		String id = "1";
		Long qtde = 5L;
		plan.setClima(clima);
		planeta.setClima(clima);
		plan.setNome(nome);
		planeta.setNome(nome);
		plan.setTerreno(terreno);
		planeta.setTerreno(terreno);
		planeta.setQtdeFilmes(qtde);
		when(repository.findById(id)).thenReturn(Optional.of(plan));
		PlanetaDTO result = service.pesquisarPlanetaId(id);
		assertEquals(result.getClima(), "seco");
		assertEquals(result.getTerreno(), "arenoso");
		assertEquals(result.getNome(), "Tatooine");
		assertEquals(result.getQtdeFilmes(), 5);
	}
	
	@Test
	public void removerPlaneta() throws Exception {
		String id = "1";
		when(repository.existsById(id)).thenReturn(true);
        service.removerPlaneta(id);
        verify(repository).deleteById(any()); 
	}
	
	@Test
	public void removerPlanetaNaoExistente() throws Exception {
		String id = "2";
		when(repository.existsById(id)).thenReturn(false);
        service.removerPlaneta(id);
        verify(repository,times(0)).deleteById(eq(id)); 
	}

}