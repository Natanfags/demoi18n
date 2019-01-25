# Demo i18n com Spring Boot

## O que é i18n
**_Internacionalização_** ou **_i18n_**, são processos de desenvolvimento e/ou adaptação de um software, para uma língua e cultura de um país. Onde torna possível a criação de "dicionários" de idiomas para customizar mensagens do sistema, como erros, validações, etc.
Para saber um pouco mais sobre a nomenclatura i18n e alguns fundamentos:
- [O que o Wikipédia fala sobre i18n](https://pt.wikipedia.org/wiki/Internacionaliza%C3%A7%C3%A3o_(inform%C3%A1tica)) .
- [i18n de mensagens de validações](https://www.baeldung.com/spring-custom-validation-message-source) .

## Sobre a Demo
*O **_i18n_** permite alterar o idioma de mensagens do servidor baseado em um "Resource Bundle `messages`" onde através da uma implementação de `{chave},"valor"` é identificado a chave alterando para o valor solicitado, reconhecendo seu idioma default ou apontando algum idioma implementado. Também foi implementado nesta demo um meio de `interpolação` entre a chave a ser traduzida + mensagens do servidor, retornando mensagens padronizadas para os idiomas implementados.*

## Executar a Demo na minha máquina

### Ambiente necessário:
- Maven 3.x.x
- Java 8 ou superior
- Postman v6.x.x
### Executando:
_1_. Clonar o projeto já concluído e importar na sua IDE como _`Maven Project`_<br>

_2_. ![Executar a aplicação pelo `Tomcat` dando run da `DemoApplication`](https://github.com/Natanfags/demo/blob/master/images/tomcatRunning.PNG)<br>

_3_. Enviar uma requisição _`GET`_ no _Postman_ com a URL do idioma que quiser testar. Este é uma método de teste simples, onde foi criado uma frase de teste e adicionado suas respectivas traduções no _`Resource Bundle`_ da Demo. 
Exemplo: <br>

- para Inglês http://localhost:8080/api/messages/en-US
![](https://github.com/Natanfags/demo/blob/master/images/postmanHwUS.PNG)<br>
- para Português http://localhost:8080/api/messages/pt-BR
![](https://github.com/Natanfags/demo/blob/master/images/postmanHwBR.PNG)<br>
- para Espanhol http://localhost:8080/api/messages/es-ES
![](https://github.com/Natanfags/demo/blob/master/images/postmanHwES.PNG)<br>

_4_. Para testar mensagens que serão enviados do sistema + mensagem passada pelo servidor via URL, utilize o seguinte método. Exemplo:<br>

- para Inglês http://localhost:8080/api/messages/args/en-US?args=teste
![](https://github.com/Natanfags/demo/blob/master/images/postmanArgsUS.PNG)<br>
- para Português http://localhost:8080/api/messages/args/pt-BR?args=teste
![](https://github.com/Natanfags/demo/blob/master/images/postmanArgsBR.PNG)<br>
- para Espanhol http://localhost:8080/api/messages/args/es-ES?args=teste
![](https://github.com/Natanfags/demo/blob/master/images/postmanArgsES.PNG)<br>

### Testando a internacionalização de mensagens do sistema:

_1_. A demo utilizara o pacote de idiomas do seu sistema operacional para _setar_ como _idioma default_, e assim, traduzir as mensagens que o servidor deve retornar quando uma requisição de validação não for atendida. Você pode também _setar_ o _idioma default_ manualmente na classe _`DemoApplication`_, passando a o idioma como _`Locale.setDefault`_, como no exemplo a baixo:

```java
@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    Locale.setDefault(new Locale("pt","BR"));
    SpringApplication.run(DemoApplication.class, args);
  }
}
```
_2_. Enviar uma requisição _`POST`_ no _Postman_ com a extensão da URL chamada _`validperson`_, o _postman_ retornara a mensagem de erro de validação com as mensagens configuradas traduzidas, baseado no seu idioma configurado, como nas imagens a baixo.

![en-US](https://github.com/Natanfags/demo/blob/master/images/postmanValidPersonUS.PNG).<br>
![pt-BR](https://github.com/Natanfags/demo/blob/master/images/postmanValidPersonBR.PNG).

## Passo a passo de desenvolvimento:
_1_. Criar um projeto [Spring Boot](https://start.spring.io/) e adicionar as dependências:

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
_2_. Implementar a _classe_ _`CustomMessageSourceConfiguration`_ e configurar dois _`@Bean`_, um para tratar as mensagens(_MessageSource_) e outro para validação da _classe_ _`Person`_ que ser implementado na etapa _4_:<br>
- O _`MessageSource`_ delegará as mensagens, configurando um _`classpath:"endereço do Resource Bundle"`_ e configurando também o tipo de _encoding_ que será utilizado.

```java
 @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
```  
- O _`LocalValidatorFactoryBean`_ validará as os campos na _classe_ _`Person`_ retornando mensagem de _`@NotNull`_ e _`@NotEmpty`_ será tratado pelo _`MessageSource`_ e retornará o _valor_ correspondente a configuração do nosso _"dicionário"_.
```java
 @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
```

_3_. Configurar um _`Resource Bundle`_ com nome _`messages`_ seguindo por _`"abreviação do idioma"`_ podendo criar quantos _"dicionários"_ forem necessários para variados idiomas. Exemplo:<br> 

```properties
messages_en.properties
messages_pt.properties
messages_es.properties
```

_4_. Criar um _`domain`_ para testar o método _`POST`_ de traduzir mensagens. Exemplo uma _classe_ _`Person`_ que valide e-mail e senha e retorne uma mensagem informando que o campo não pode ser vazio. Exemplo a baixo:

```java
public class Person {

    @NotEmpty(message = "{email.required}")
    @Email
    private String email;

    @NotNull(message = "{pw.required}")
    private String password;
    //getter and setter a baixo
```

_5_. Para criar suas mensagens no _`Resource Bundle`_ de _messages_ basta passar uma _`chave`_ e o _`valor`_ que será atribuído e que, referencie a tradução para o idioma aplicado, onde a _`chave`_ será substituída no sistema pelos valores atribuídos. Exemplo:<br>

_`messages_en.properties`_:
```properties
message.hello.world=Hello World
message.received=Message received
```
_`messages_pt.properties`_
```properties
message.hello.world=Olá mundo
message.received=Mensagen recebida
```
_`messages_es.propert`_
```properties
message.hello.world=Holla Mundo
message.received=Mensaje recibido
```
