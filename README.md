
# Projeto de Automção de Testes - SauceDemo

Este projeto contém testes automatizados desenvolvidos em **Java** utilizando **Cucumber/Gherkin** para o site SauceDemo. O objetivo é validar funcionalidades críticas da aplicação.

Ele também permite a escolha entre os navegadores **Google Chrome** e **Firefox** e possui integração com **GitHub Actions** para CI/CD.

## 📋 Pré-requisitos

Certifique-se de ter os seguintes itens instalados:

-   **Java JDK 11** ou superior
    
-   **Apache Maven**
    
-   **Google Chrome** e/ou **Firefox**
    
-   **Git**
    
-   Um editor de código (recomendado: **Visual Studio Code** ou **IntelliJ IDEA**)
    

### Verifique a instalação do Java e Maven:

```
java -version
mvn -version
```

## 🚀 Começando

Abra o terminal e clone o repositório:

```
git clone https://github.com/nataliamacmac/qa-test.git
```

Acesse a pasta do projeto:

```
cd qa-test
```

## ⚙️ Configuração do Projeto

### Drivers do Chrome e Firefox:

Certifique-se de que os drivers para o Chrome e Firefox estão configurados corretamente no **PATH** ou na pasta do projeto.

#### Baixe os drivers (Se necessário):

-   **ChromeDriver**: [Download ChromeDriver](https://chromedriver.chromium.org/downloads)
    
-   **GeckoDriver (Firefox)**: [Download GeckoDriver](https://github.com/mozilla/geckodriver/releases)
    

Coloque o driver correspondente ao seu sistema operacional na pasta `/drivers` do projeto e atualize o caminho nas configurações, se necessário.

### Arquivo `config.properties`

Este projeto usa um arquivo `config.properties` para definir algumas configurações importantes para a execução dos testes. Abaixo estão as propriedades necessárias que você deve configurar:

-   **browser**: Define qual navegador será utilizado para os testes. Pode ser `chrome` ou `firefox`.
    
-   **chromeDriver**: Caminho para o executável do ChromeDriver, caso não esteja no PATH do sistema. Exemplo: `C:/Automacao/drivers/chromedriver.exe`
    
-   **geckoDriver**: Caminho para o executável do GeckoDriver, caso não esteja no PATH do sistema. Exemplo: `C:/Automacao/drivers/geckodriver.exe`
    
-   **environment**: Define o ambiente em que os testes serão executados. Pode ser `local` ou `ci`. Quando definido como `ci`, serão aplicadas configurações específicas para execução em pipelines de CI/CD, como GitHub Actions.
    

Exemplo de arquivo `config.properties`:

❗ **IMPORTANTE**: Sempre que realizar um commit ou pull request do arquivo `config.properties`, certifique-se de alterar o valor de `environment` para `ci`.

```
browser=chrome
chromeDriver=C:/Automacao/drivers/chromedriver.exe
geckoDriver=C:/Automacao/drivers/geckodriver.exe
environment=local
```
## ▶️ Como Executar os Testes

### Escolhendo o Navegador

No arquivo de configuração `config.properties`, você pode definir o navegador a ser utilizado:

```
browser=chrome
```

Use **chrome** ou **firefox** como valor.

### Executando os Testes

No terminal, dentro da pasta do projeto, execute:

```
mvn clean test -Dbrowser=chrome
```

Ou para rodar no Firefox:

```
mvn clean test -Dbrowser=firefox
```

## 📊 Relatórios Gerados

Após a execução, um relatório será gerado automaticamente na pasta `target/cucumber-reports`.

## 🔄 Integração com CI/CD

Este projeto já está configurado para integração com **GitHub Actions**. A cada push na branch **main**, o pipeline de testes será executado automaticamente.

Você pode verificar o status das execuções na aba **Actions** do repositório.

> ⚠️ **Nota:** A integração com CI/CD ainda não foi testada por restrições na máquina de origem.

## 🤝 Contribuições

Sinta-se à vontade para abrir **issues** e **pull requests** para melhorias e correções.

## 📞 Contato

Caso tenha dúvidas ou problemas, entre em contato:

-   **Autor**: Natália Mac
    
-   **GitHub**: [nataliamacmac](https://github.com/nataliamacmac)