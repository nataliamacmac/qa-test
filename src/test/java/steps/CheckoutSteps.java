package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import org.junit.Assert;
import utils.Utilidades;

public class CheckoutSteps {
    WebDriver driver;
    Utilidades util;
    
    @FindBy(id = "user-name")
    WebElement campoUsuario;

    @FindBy(id = "password")
    WebElement campoSenha;

    @FindBy(id = "login-button")
    WebElement botaoLogin;

    @FindBy(xpath = "//*[@id='inventory_container']/div/div[1]/div[3]/button")
    WebElement botaoAdicionarCarrinho;

    @FindBy(id = "shopping_cart_container")
    WebElement botaoCarrinho;

    @FindBy(xpath = "//*[@class='btn_action checkout_button']")
    WebElement botaoCheckout;

    @FindBy(id = "first-name")
    WebElement campoNome;

    @FindBy(id = "last-name")
    WebElement campoSobrenome;

    @FindBy(id = "postal-code")
    WebElement campoCep;

    @FindBy(xpath = "//*[@class='btn_primary cart_button']")
    WebElement botaoContinuar;

    @FindBy(xpath = "//*[@class='cart_cancel_link btn_secondary']")
    WebElement botaoCancelar;

    @FindBy(xpath = "//*[@class='btn_action cart_button']")
    WebElement botaoFinalizar;

    @FindBy(className = "complete-header")
    WebElement mensagemConfirmacao;

    @FindBy(css = "h3[data-test='error']")
    WebElement mensagemErro;

    @FindBy(className = "cart_item")
    WebElement itemCarrinho;
    
    @FindBy(xpath = "//*[@class='btn_secondary cart_button']")
    WebElement botaoRemover;
    
    @FindBy(xpath = "//*[@class='cart_quantity']")
    WebElement quantidadeCarrinho;
    
    
    public CheckoutSteps() {
        PageFactory.initElements(driver, this);
    }

    @Given("acessar sistema saucedemo")
    public void acessarSistema() {
        configurarDriver();
        driver.get("https://www.saucedemo.com/v1/");
        util = new Utilidades();
        util.preencherCampo(campoUsuario, "standard_user");
        util.preencherCampo(campoSenha, "secret_sauce");
        util.clicarElemento(botaoLogin);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @When("adiciono o primeiro item da página ao carrinho")
    public void adicionarSauceLabsBackpackAoCarrinho() {
    	util.clicarElemento(botaoAdicionarCarrinho);
    }

    @And("acesso o carrinho e clico em checkout")
    public void acessarCarrinhoFazerndoCheckout() {
        util.clicarElemento(botaoCarrinho);
        util.clicarElemento(botaoCheckout);
    }

    @When("preencho no formulário de checkout nome, sobrenome, and cep com dados válidos")
    public void preencheFormularioCheckoutComDadosValidos() {
        util.preencherCampo(campoNome, "Natalia");
        util.preencherCampo(campoSobrenome, "Machado");
        util.preencherCampo(campoCep, "36000");
    }

    @When("Eu deixo os campos First Name, Last Name e Postal Code em branco")
    public void deixaFormularioCheckoutEmBranco() {
        util.preencherCampo(campoNome, "");
        util.preencherCampo(campoSobrenome, "");
        util.preencherCampo(campoCep, "");
    }

    @When("clico em Continue")
    public void clicarContinuar() {
    	util.clicarElemento(botaoContinuar);
    }

    @When("clico em cancelar e retorno a tela anterior")
    public void clicarCancelar() {
    	util.clicarElemento(botaoCancelar);
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
        driver.quit();
    }

    @When("clico no botão em Finish")
    public void clicarFinalizar() {
    	util.clicarElemento(botaoFinalizar);
    }

    @Then("o pedido é finalizado com sucesso e exibe mensagem de confirmação")
    public void pedidoComSucesso() {
        Assert.assertTrue(util.elementoEstaVisivel(mensagemConfirmacao));
        driver.quit();
    }

    @Then("apresenta a mensagem de erro Error First Name is required")
    public void mensagemErroCamposObrigatorios() {
        Assert.assertTrue(util.elementoEstaVisivel(mensagemErro));
        mensagemErro.getText().contains("Error");
        Assert.assertTrue(mensagemErro.getText().contains("Error: First Name is required"));
        driver.quit();
    }

    @Then("mostra o resumo das quantidades e valores")
    public void validarTelaFinalComResumo() {
        Assert.assertTrue("O item do carrinho não está visível.", util.elementoEstaVisivel(itemCarrinho));
        
        WebElement nomeProduto = driver.findElement(By.className("inventory_item_name"));
        Assert.assertEquals("Nome do produto está incorreto.", "Sauce Labs Backpack", nomeProduto.getText());
        
        WebElement quantidadeProduto = driver.findElement(By.className("summary_quantity"));
        Assert.assertEquals("A quantidade do produto está incorreta.", "1", quantidadeProduto.getText());
        
        WebElement precoProduto = driver.findElement(By.className("summary_subtotal_label"));
        Assert.assertTrue("O preço do produto está incorreto.", precoProduto.getText().contains("$29.99"));
        
        WebElement subtotal = driver.findElement(By.className("summary_total_label"));
        Assert.assertTrue("O subtotal do pedido está incorreto.", subtotal.getText().contains("$32.39"));

        driver.quit();
    }
    
    @When("eu removo o item")
    public void reomveItem() {
        util.clicarElemento(botaoRemover);
    }
   
    @Then("o carrinho tem sua quantidade atualizada")
    public void validaCarrinhoVazio() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("cart_item"), 0));

        int quantidadeItensNoCarrinho = driver.findElements(By.className("cart_item")).size();
        Assert.assertEquals("O carrinho está vazio.", 0, quantidadeItensNoCarrinho);
        driver.quit();
    }

    public void configurarDriver() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:/Automacao/Drivers/113");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "C:/Automacao/Drivers/113/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        PageFactory.initElements(driver, this);
    }
}


