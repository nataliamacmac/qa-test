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

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
        esperarSerVisivel(campoUsuario,2);
        util.preencherCampo(campoUsuario, "standard_user");
        util.preencherCampo(campoSenha, "secret_sauce");
        util.clicarElemento(botaoLogin);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @When("adiciono o primeiro item da página ao carrinho")
    public void adicionarSauceLabsBackpackAoCarrinho() {
    	esperarSerVisivel(botaoAdicionarCarrinho,2);
    	util.clicarElemento(botaoAdicionarCarrinho);
    	util.espera(1);
    }

    @And("acesso o carrinho e clico em checkout")
    public void acessarCarrinhoFazerndoCheckout() {
    	esperarSerVisivel(botaoCarrinho,2);
        util.clicarElemento(botaoCarrinho);
        util.clicarElemento(botaoCheckout);
        util.espera(1);
    }

    @When("preencho no formulário de checkout nome, sobrenome, and cep com dados válidos")
    public void preencheFormularioCheckoutComDadosValidos() {
    	esperarSerVisivel(campoNome,2);
        util.preencherCampo(campoNome, "Natalia");
        util.preencherCampo(campoSobrenome, "Machado");
        util.preencherCampo(campoCep, "36000");
        util.espera(1);
    }

    @When("Eu deixo os campos First Name, Last Name e Postal Code em branco")
    public void deixaFormularioCheckoutEmBranco() {
    	esperarSerVisivel(campoNome,2);
        util.preencherCampo(campoNome, "");
        util.preencherCampo(campoSobrenome, "");
        util.preencherCampo(campoCep, "");
        util.espera(1);
    }

    @When("clico em Continue")
    public void clicarContinuar() {
    	esperarSerVisivel(botaoContinuar,2);
    	util.clicarElemento(botaoContinuar);
    	util.espera(1);
    }

    @When("clico em cancelar e retorno a tela anterior")
    public void clicarCancelar() {
    	esperarSerVisivel(botaoCancelar,2);
    	util.clicarElemento(botaoCancelar);
    	util.espera(1);
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
        driver.quit();
    }

    @When("clico em cancelar e retorno a tela anterior antes de encerrar")
    public void clicarCancelarAntesDeEncerrar() {
    	esperarSerVisivel(botaoCancelar,2);
    	util.clicarElemento(botaoCancelar);
    	util.espera(1);
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }

    @When("clico no botão em Finish")
    public void clicarFinalizar() {
    	esperarSerVisivel(botaoFinalizar,2);
    	util.clicarElemento(botaoFinalizar);
    	util.espera(1);
    }

    @Then("o pedido é finalizado com sucesso e exibe mensagem de confirmação")
    public void pedidoComSucesso() {
    	util.espera(1);
        Assert.assertTrue(util.elementoEstaVisivel(mensagemConfirmacao));
        driver.quit();
    }

    @Then("apresenta a mensagem de erro Error First Name is required")
    public void mensagemErroCamposObrigatorios() {
    	util.espera(1);
        Assert.assertTrue(util.elementoEstaVisivel(mensagemErro));
        
        Assert.assertTrue(mensagemErro.getText().contains("Error: First Name is required"));
        driver.quit();
    }

    @Then("mostra o resumo das quantidades e valores")
    public void validarTelaFinalComResumo() {
    	util.espera(1);
        Assert.assertTrue("O item do carrinho está visível.", util.elementoEstaVisivel(itemCarrinho));
        
        WebElement nomeProduto = driver.findElement(By.className("inventory_item_name"));
        Assert.assertEquals("Nome do produto está correto.", "Sauce Labs Backpack", nomeProduto.getText());
        
        WebElement quantidadeProduto = driver.findElement(By.className("summary_quantity"));
        Assert.assertEquals("A quantidade do produto está correta.", "1", quantidadeProduto.getText());
        
        WebElement precoProduto = driver.findElement(By.className("summary_subtotal_label"));
        Assert.assertTrue("O preço do produto está correto.", precoProduto.getText().contains("$29.99"));
        
        WebElement subtotal = driver.findElement(By.className("summary_total_label"));
        Assert.assertTrue("O subtotal do pedido está correto.", subtotal.getText().contains("$32.39"));

        driver.quit();
    }
    
    @When("eu removo o item")
    public void reomveItem() {
    	esperarSerVisivel(botaoRemover,4);
        util.clicarElemento(botaoRemover);
        util.espera(1);
    }
   
    @Then("o carrinho tem sua quantidade atualizada")
    public void validaCarrinhoVazio() {
    	util.espera(1);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("cart_item"), 0));

        int quantidadeItensNoCarrinho = driver.findElements(By.className("cart_item")).size();
        Assert.assertEquals("O carrinho está vazio.", 0, quantidadeItensNoCarrinho);
        driver.quit();
    }

    public void configurarDriver() {
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("config.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possível carregar o arquivo config.properties");
        }

        String browser = prop.getProperty("browser", "chrome").toLowerCase();
        String chromeDriver = prop.getProperty("chromeDriver", "C:/Automacao/Drivers/113/chromedriver.exe").toLowerCase();
        String geckoDriver = prop.getProperty("geckoDriver", "C:/Automacao/Drivers/113/geckoDriver.exe").toLowerCase();

        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", geckoDriver);
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        PageFactory.initElements(driver, this);
    }
    
	public boolean esperarSerVisivel(WebElement elemento, int tempoLimiteDeEspera) {
		boolean elementoVisivel = false;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tempoLimiteDeEspera));
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(elemento)));
			wait.until(ExpectedConditions.visibilityOf(elemento));
			elementoVisivel = true;
		return elementoVisivel;
	}
    
}


