package br.com.khadije.zein.starwarsapi.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.khadije.zein.starwarsapi.domain.Planeta;
import br.com.khadije.zein.starwarsapi.repository.PlanetaRepository;
import br.com.khadije.zein.starwarsapi.utils.MethodUtils;



@TestInstance(Lifecycle.PER_CLASS)
class PlanetaServiceTest {

	@Mock
	private PlanetaRepository repository;
	
	@Mock
	private MethodUtils methodUtils;
	
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
		when(repository.save(planeta))
		.thenReturn(null);
		//when(methodUtils.callAPI("https://swapi.dev/api/planets/?search=")).thenReturn(null);
		Boolean result = service.adicionarPlaneta("", "seco", "arenoso");
		assertEquals(null, result);
	}
	
	@Test
	public void adicionarPlanetaClimaVazio() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setClima("");
		planeta.setNome("Tatooine");
		planeta.setTerreno("arenoso");
		when(repository.save(planeta))
		.thenReturn(null);

		Boolean result = service.adicionarPlaneta("Tatooine", "", "arenoso");
		assertEquals(null, result);
	}
	
	@Test
	public void adicionarPlanetaTerrenoVazio() throws Exception {
			Planeta planeta = new Planeta();
			planeta.setClima("seco");
			planeta.setNome("Tatooine");
			planeta.setTerreno("");
			when(repository.save(planeta))
			.thenReturn(null);

			Boolean result = service.adicionarPlaneta("Tatooine", "seco", "");
			assertEquals(null, result);
	}
	
	@Test
	public void adicionarPlanetaCamposVazios() throws Exception{
		Planeta planeta = new Planeta();
		planeta.setClima("");
		planeta.setNome("");
		planeta.setTerreno("");
		when(repository.save(planeta))
		.thenReturn(null);

		Boolean result = service.adicionarPlaneta("", "", "");
		assertEquals(null, result);
	}
	
	
	@Test
	public void adicionarPlanetaInvalido() throws Exception {
		Planeta planeta = new Planeta();
		planeta.setClima("seco");
		planeta.setNome("Lol");
		planeta.setTerreno("arenoso");
		when(repository.save(planeta))
		.thenReturn(null);

		Boolean result = service.adicionarPlaneta("Lol", "seco", "arenoso");
		assertEquals(null, result);
	}
	
	
	/*@Test
	public void adicionarPlanetaInvalido() {
		Historico historico = new Historico();
		historico.setDataConsulta(LocalDateTime.now());
		historico.setEscalaTemperatura("kelvin");
		historico.setTemperatura(20d);
		when(repository.save(historico))
		.thenReturn(null);
		Historico result = facade.criarHistorico(new ConversorTemperatura(20d,"kelvin"), null);
		assertEquals(null, result);
	}
	
	
	@Test
	public void adicionarPlanetaDuplicado() {
		Historico historico = new Historico();
		historico.setDataConsulta(LocalDateTime.now());
		historico.setEscalaTemperatura("kelvin");
		historico.setTemperatura(20d);
		when(repository.save(historico))
		.thenReturn(null);
		Historico result = facade.criarHistorico(new ConversorTemperatura(20d,"kelvin"), null);
		assertEquals(null, result);
	}

	@Test
	public void adicionarPlanetaDadosValidos() {
		Historico historico = new Historico();
		String escala = "celcius";
		Double temperatura = 20d;
		String resultado = "68.0 °F";
		historico.setDataConsulta(LocalDateTime.now());
		historico.setEscalaTemperatura(escala);
		historico.setTemperatura(temperatura);
		historico.setResultadoConversao(resultado);
		when(repository.save(any(Historico.class)))
		.thenReturn(historico);
		Historico result = facade.criarHistorico(new ConversorTemperatura(temperatura,escala), resultado);

		assertThat(result.getEscalaTemperatura(), equalTo("celcius"));
		assertThat(result.getResultadoConversao(), equalTo("68.0 °F"));
		assertThat(result.getTemperatura(), equalTo(20d));
	}
	
	@Test
	public void listarPlanetasSemValores() {
		when(repository.findAll())
		.thenReturn(null);
		List<Historico> result = facade.getHistorico();
		assertEquals(null, result);
	}
	
	@Test
	public void listarPlanetas() {
		List<Historico> historicos = new ArrayList<>();
		Historico historico = new Historico();
		String escala = "celcius";
		Double temperatura = 20d;
		String resultado = "68.0 °F";
		historico.setDataConsulta(LocalDateTime.now());
		historico.setEscalaTemperatura(escala);
		historico.setTemperatura(temperatura);
		historico.setResultadoConversao(resultado);
		historicos.add(historico);
		when(repository.findAll())
		.thenReturn(historicos);
		List<Historico> result = facade.getHistorico();
		 assertThat(result.size(), equalTo(1));
		Historico resultHistorico = result.get(0);
		assertThat(resultHistorico.getEscalaTemperatura(), equalTo("celcius"));
		assertThat(resultHistorico.getResultadoConversao(), equalTo("68.0 °F"));
		assertThat(resultHistorico.getTemperatura(), equalTo(20d));
	}*/
	
}