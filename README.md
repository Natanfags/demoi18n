# Demo i18n com Spring Boot

## O que é i18n
**_Internacionalização_** ou **_i18n_**, são processos de desenvolvimento e/ou adaptação de um software, para uma língua e cultura de um país.
Para saber um pouco mais sobre a nomeclartura i18n e alguns fundamentos.
- https://pt.wikipedia.org/wiki/Internacionaliza%C3%A7%C3%A3o_(inform%C3%A1tica)
- [i18n de mensagens de validações](https://www.baeldung.com/spring-custom-validation-message-source).

## Sobre a Demo
*O **_i18n_** permite alterar o idioma de mensagens do servidor baseado em um "Resource Bundle `messages`" onde astravés da uma implementação de `{chave} "valor"`é identificado a chave alterando para o valor do idioma solicitado.*

## Executar a Demo na minha máquina

### Ambiente necessário:
- Maven 3.x.x
- Java 8 ou superior
- Postman v6.x.x
### Executando:
**1**.Clonar o projeto e importar na sua IDE como `Maven Project`<br>

**2**.![Executar a aplicação pelo `Tomcat` dando run da `DemoApplication`](/images/tomcatRunning.png)<br>

**3**.Enviar uma requisição `GET` no Postman com a URL do idioma que quiser testar. Este é uma método de teste simples, onde foi criado uma frase de teste e adicionado suas respectivas traduções no *Resource Bundle* da Demo.
Exemplo: <br>
- para Espanhol http://localhost:8080/api/es-ES/ <br>
- para Ingles http://localhost:8080/api/en-US/ <br>
- para Português http://localhost:8080/api/pt-BR/ <br>

**4**.Para testar mensagens que serão enviados do sistema seguindo de alguma mensagen passada do servidor pela URL utilize o seguinte método. Exemplo:<br>
- para Espanhol http://localhost:8080/api/es-ES/message?teste <br>
- para Ingles http://localhost:8080/api/en-US/message?teste <br>
- para Português http://localhost:8080/api/pt-BR/message?teste <br>

![O servidor retornara uma mensagem traduzida "`Mensagem recebida`" interpolando com uma mensagem que o servidor ira passar.](/images/postmanResultEx.png)

### Passo a passo de desenvolvimento:
**1**.Criar um projeto [Spring Boot](https://start.spring.io/) e adicionar as dependências:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
**2**.Implementar o *MessageSource* como um *Bean* para delegação das mensagens, configurando um *`classpath:"endereço do Resource Bundle`* e configurando tambem o tipo de encoding que sera utilizado.

```java
 @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
```  
**3**.Configurar um *Resource Bundle* com nome `messages` seguindo por `_"abreviação do idioma"` com extensão `.properties` podendo criar quantos *dicionarios* for necessario para variados idiomas. Exemplo:<br> 

```properties
messages_en.properties
messages_pt.properties
messages_es.properties
```

**4**.Criando suas mensagens no *Resource Bundle* de messages passando uma *chave* e o valor que sera atribuido referente a tradução pro idioma aplicado. Onde a chave sera substituida no sistema pelos valores atribuidos. Exemplo:<br>
```properties
message.hello.world=Olá mundo
message.received=Mensagen recebida

message.hello.world=Hello World
message.received=Message received

message.hello.world=Hello World
message.received=Message received
```
