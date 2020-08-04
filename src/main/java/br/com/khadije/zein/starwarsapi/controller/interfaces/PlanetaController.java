package br.com.khadije.zein.starwarsapi.controller.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.khadije.zein.starwarsapi.exception.ApiError;
import br.com.khadije.zein.starwarsapi.exception.BusinessException;
import br.com.khadije.zein.starwarsapi.exception.NoContentException;
import br.com.khadije.zein.starwarsapi.exception.RequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Interface do controller REST de planetas de star wars.
 */

@Api(tags = { "Planeta" })
@RequestMapping(PlanetaController.PATH)
public interface PlanetaController {
	final String PATH = "/planetas";
	
	@ApiOperation(value = "adicionarPlaneta", tags = { "Planeta" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@ApiResponses({ //
	        @ApiResponse(code = 200, message = "Planeta adicionado com sucesso", response = String.class, responseContainer = "String"), //
	        @ApiResponse(code = 204, message = "Valor nulo retornado", response = NoContentException.class),
	        @ApiResponse(code = 400, message = "Erro na regra de negócio", response = BusinessException.class), //
	        @ApiResponse(code= 412, message = "Erro de validação na requisição",response = ApiError.class),
	        @ApiResponse(code = 500, message = "Erro inesperado", response = RequestException.class) //
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> adicionarPlaneta(
    		@ApiParam(value = "nome do planeta", required = true, example = "Tatooine") 
    		@RequestHeader String nome,
    		@ApiParam(value = "clima do planeta", required = true, example = "Seco") 
    		@RequestHeader String clima, 
    		@ApiParam(value = "terreno do planeta", required = true, example = "Arenoso") 
    		@RequestHeader String terreno) throws Exception;
	
	
	@ApiOperation(value = "listarPlanetas", tags = { "Planeta" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@ApiResponses({ //
        @ApiResponse(code = 200, message = "Planeta listado com sucesso", response = String.class, responseContainer = "String"), //
        @ApiResponse(code = 204, message = "Valor nulo retornado", response = NoContentException.class),
        @ApiResponse(code = 500, message = "Erro inesperado", response = RequestException.class) //
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> listarPlanetas();
	
	
	@ApiOperation(value = "pesquisarPlanetaId", tags = { "Planeta" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@ApiResponses({ //
	        @ApiResponse(code = 200, message = "Planeta pesquisado com sucesso", response = String.class, responseContainer = "String"), //
	        @ApiResponse(code = 204, message = "Valor nulo retornado", response = NoContentException.class),
	        @ApiResponse(code= 412, message = "Erro de validação na requisição",response = ApiError.class),
	        @ApiResponse(code = 500, message = "Erro inesperado", response = RequestException.class) //
	})
	@GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> pesquisarPlanetaId(
    		@ApiParam(value = "id do planeta", required = true, example = "1") 
			@PathVariable("id") String id);
	
	@ApiOperation(value = "pesquisarPlanetaNome", tags = { "Planeta" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@ApiResponses({ //
	        @ApiResponse(code = 200, message = "Planeta pesquisado com sucesso", response = String.class, responseContainer = "String"), //
	        @ApiResponse(code = 204, message = "Valor nulo retornado", response = NoContentException.class),
	        @ApiResponse(code= 412, message = "Erro de validação na requisição",response = ApiError.class),
	        @ApiResponse(code = 500, message = "Erro inesperado", response = RequestException.class) //
	})
	@GetMapping(params="nome",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> pesquisarPlanetaNome(
    		@ApiParam(value = "nome do planeta", required = true, example = "Tatooine") 
    		@RequestParam("nome") String nome);
	
	@ApiOperation(value = "removerPlaneta", tags = { "Planeta" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@ApiResponses({ //
	        @ApiResponse(code = 200, message = "Planeta removido com sucesso", response = String.class, responseContainer = "String"), //
	        @ApiResponse(code = 204, message = "Valor nulo retornado", response = NoContentException.class),
	        @ApiResponse(code= 412, message = "Erro de validação na requisição",response = ApiError.class),
	        @ApiResponse(code = 500, message = "Erro inesperado", response = RequestException.class) //
	})
	@DeleteMapping(value="{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> removerPlaneta(
    		@ApiParam(value = "id do planeta", required = true, example = "1") 
			@PathVariable("id") String id);
}

