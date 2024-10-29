Projeto de Automação de Testes - SauceDemo
Este projeto contém testes automatizados desenvolvidos em Java utilizando Cucumber/Gherkin para o site SauceDemo. O objetivo é validar funcionalidades críticas da aplicação.
Ele também permite a escolha entre os navegadores Google Chrome e Firefox e possui integração com GitHub Actions para CI/CD.

Pré-requisitos
Certifique-se de ter os seguintes itens instalados:

Java JDK 11 ou superior
Apache Maven
Google Chrome e/ou Firefox
Git
Um editor de código (recomendado: Visual Studio Code ou IntelliJ IDEA)
Verifique a instalação do Java e Maven:

java -version
mvn -version

Abra o terminal e clone o repositório:
git clone https://github.com/nataliamacmac/qa-test.git

Acesse a pasta do projeto:
qa-test

Configuração do Projeto
Drivers do Chrome e Firefox:
Certifique-se de que os drivers para o Chrome e Firefox estão configurados corretamente no PATH ou na pasta do projeto.

Baixe os drivers (Se necessário):
ChromeDriver: https://chromedriver.chromium.org/downloads
GeckoDriver (Firefox): https://github.com/mozilla/geckodriver/releases
Coloque o driver correspondente ao seu sistema operacional na pasta /drivers do projeto e atualize o caminho nas configurações, se necessário.

Como Executar os Testes
1. Escolhendo o Navegador
No arquivo de configuração config.properties, você pode definir o navegador a ser utilizado:

properties
browser=chrome
Use chrome ou firefox como valor.

No terminal, dentro da pasta do projeto, execute:
mvn clean test -Dbrowser=chrome

Ou para rodar no firefox:
mvn clean test -Dbrowser=firefox

Relatórios Gerados
Após a execução, um relatório será gerado automaticamente na pasta target/cucumber-reports.

Integração com CI/CD (Não testado por restrições na máquina de origem).
Este projeto já está configurado para integração com GitHub Actions. 
A cada push na branch main, o pipeline de testes será executado automaticamente.
Você pode verificar o status das execuções na aba Actions do repositório.

Contribuições
Sinta-se à vontade para abrir issues e pull requests para melhorias e correções.

Contato
Caso tenha dúvidas ou problemas, entre em contato:

Autor: Natália Mac
