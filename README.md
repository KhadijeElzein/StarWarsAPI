# StarWarsAPI
API com informações sobre o mundo star wars

Para compilar e rodar a API:
* Instale o Maven na sua máquina
  * Link com instruções de instalação:
    * No Windows: https://howtodoinjava.com/maven/how-to-install-maven-on-windows/
    * No Linux e No MacOs:https://www.baeldung.com/install-maven-on-windows-linux-mac
* Compile o programa. Para compilar:
  * No terminal, vá até a pasta onde se encontra o pom.xml, e digite : 
    
    **mvn clean package**
    
    
* Para executar os testes:
  * No terminal, estando na pasta onde se encontra o pom.xml, digite : 
  
    **mvn test**

* Para executar a API:
  * No terminal, estando na pasta onde se encontra o pom.xml, vá até a pasta target, onde está o jar da aplicação. Digite:
  
    **java -jar starwarsapi-0.0.1-SNAPSHOT.jar**
    
Observação: As configurações de conexão ao banco estão de acordo com o heroku. Para rodar localmente descomentar as configurações locais do mongodb no application.properties    

Link para o heroku: https://star-wars-api-spring.herokuapp.com/starwars/
Link para o swagger: https://star-wars-api-spring.herokuapp.com/starwars/swagger-ui.html
