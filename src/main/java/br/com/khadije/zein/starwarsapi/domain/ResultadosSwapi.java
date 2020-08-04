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
public class ResultadosSwapi implements Serializable{
private static final long serialVersionUID = -3177491365691298750L;
  private String name;
  List<String> films;
}