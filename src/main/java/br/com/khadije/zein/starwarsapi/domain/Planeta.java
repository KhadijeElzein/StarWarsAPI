package br.com.khadije.zein.starwarsapi.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Document(collection = "planetas")
public class Planeta implements Serializable {
private static final long serialVersionUID = 6078727149331881186L;
  @Id
  private String id;
  private String nome;
  private String clima;
  private String terreno;
}