package br.com.khadije.zein.starwarsapi.utils;

import org.springframework.web.client.RestTemplate;

import br.com.khadije.zein.starwarsapi.domain.RespostaSwapi;

public class MethodUtils {
	public RespostaSwapi callAPI(String url) {
		RestTemplate restTemplate = new RestTemplate();
		RespostaSwapi result = restTemplate.getForObject(url, RespostaSwapi.class);
	    return result;
	}
}