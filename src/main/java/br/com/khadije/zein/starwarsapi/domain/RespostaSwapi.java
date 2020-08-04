package br.com.khadije.zein.starwarsapi.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@Getter
@Setter
public class RespostaSwapi implements Serializable{
private static final long serialVersionUID = -1072594144504938961L;
private Long count;
  private String next;
  private String previous;
  List<ResultadosSwapi> results;
}