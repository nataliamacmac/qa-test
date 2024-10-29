@Checkout
Feature: Funcionalidade de Checkout

  Background: Acessar sistema LEXWeb
    Given acessar sistema saucedemo

	@CT01
  Scenario: Finalização de compra com sucesso
    When adiciono o primeiro item da página ao carrinho
    And acesso o carrinho e clico em checkout
    And preencho no formulário de checkout nome, sobrenome, and cep com dados válidos
    And clico em Continue
    And clico no botão em Finish
    Then o pedido é finalizado com sucesso e exibe mensagem de confirmação

	@CT02
  Scenario: Criticar campos obrigatórios no checkout
    When adiciono o primeiro item da página ao carrinho
    And acesso o carrinho e clico em checkout
    And Eu deixo os campos First Name, Last Name e Postal Code em branco
    And clico em Continue
    Then apresenta a mensagem de erro Error First Name is required

	@CT03
  Scenario: Removendo item do carrinho
    When adiciono o primeiro item da página ao carrinho
    And acesso o carrinho e clico em checkout
    And clico em cancelar e retorno a tela anterior
    When eu removo o item
    Then o carrinho tem sua quantidade atualizada

	@CT04
  Scenario: Retornar ao carrinho após clicar em checkout
    When adiciono o primeiro item da página ao carrinho
    And acesso o carrinho e clico em checkout
    Then clico em cancelar e retorno a tela anterior

	@CT05
  Scenario: Revisão de pedido antes do checkout
    When adiciono o primeiro item da página ao carrinho
    And acesso o carrinho e clico em checkout
    And preencho no formulário de checkout nome, sobrenome, and cep com dados válidos
    And clico em Continue
    Then mostra o resumo das quantidades e valores
