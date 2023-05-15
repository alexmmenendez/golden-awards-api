# Dependências necessárias  
Para executar o teste é necessário ou você ter um docker instalado (Utilizei é a versão 23.0.2). Se preferir
 executar sem container, é necessário ter java 11 ou superior.

# Comandos para executar o projeto:
Pelo docker, via terminal realize os seguintes comandos: 

- git clone https://github.com/alexmmenendez/golden-awards-api.git
- cd golden-awards-api
- docker build -t golden-awards-api .
- docker run -p 8080:8080 golden-awards-api

Se for executar sem container eu recomendo utilizar uma IDEA, como Eclipse ou IntelliJ para clonar o projeto, e a IDEA já vai configurar
 o gradle.

Em Run/Debug Configuration selecionar a task 'bootRun' e em environment variables adicionar: 'spring.profiles.active=prod'

Feito isso execute pelo IDEA ou pelo terminal da IDEA:

- ./gradlew bootRun