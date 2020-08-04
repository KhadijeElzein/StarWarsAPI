package br.com.khadije.zein.starwarsapi.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PlanetaDTO implements Serializable {
  private static final long serialVersionUID = 3000562655635404633L;
  private String id;
  private String nome;
  private String clima;
  private String terreno;
  private Long qtdeFilmes;
}
