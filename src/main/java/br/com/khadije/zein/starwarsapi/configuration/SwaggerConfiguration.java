package br.com.khadije.zein.starwarsapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	 
	@Value("${spring.application.version}")
	private String version;
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.khadije.zein.starwarsapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info());
    }
    
    public ApiInfo info() {
        return new ApiInfoBuilder() //
                .contact(new Contact("Khadije Elisa Lopes El Zein",null, "khadyelzein@gmail.com")) //
                .title("Star Wars API") //
                .description("" //
                        + "API com todas as informações sobre Star Wars" //
                ) //
                .version(version) //
                .build();
    }
}