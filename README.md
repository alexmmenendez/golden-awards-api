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

Feito isso voce pode configurar um Run/Debug com o comando bootRun do gradle ou simplesmente
 no terminal da IDEA executar o seguinte comando: 

- ./gradlew bootRun

# Observações:
Realizei outros cenários que o csv disponibilizado não demonstrava, como um produtor ter vencido mais de 2 prêmios
. Fiquei na dúvida se seria esperado que só retornase o maior intervalo que o produtor teve ou todos os intervalos
 que teve para vencer. Optei por exibir todos os intervalos que o produtor teve para vencer.