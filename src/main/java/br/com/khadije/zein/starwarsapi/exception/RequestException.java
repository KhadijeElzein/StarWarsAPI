package br.com.khadije.zein.starwarsapi.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "requestException", description = "Erro Genérico")
@JsonInclude(value = Include.ALWAYS)
public class RequestException implements Serializable {

    private static final long serialVersionUID = 2039554643413867493L;

    @ApiModelProperty(value = "Timestamp de quando aconteceu o erro", required = true, example = "1560000000000")
    @JsonProperty(required = true)
    private Long timestamp;

    @ApiModelProperty(value = "Código do estado Http", required = true, example = "400")
    @JsonProperty(required = true)
    private Integer status;

    @ApiModelProperty(value = "Título do erro", required = false, example = "Bad Request")
    @JsonProperty(required = false)
    private String error;

    @ApiModelProperty(value = "Mensagem do erro", required = true, example = "Erro na consulta de lojas.")
    @JsonProperty(required = true)
    private String message;

    @ApiModelProperty(value = "Caminho da API", required = true, example = "/lojas")
    @JsonProperty(required = true)
    private String path;

}