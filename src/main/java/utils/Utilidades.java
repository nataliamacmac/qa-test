package utils;

import org.openqa.selenium.WebElement;


     public class Utilidades {

         public void clicarElemento(WebElement elemento) {
             elemento.click();
         }

         public void preencherCampo(WebElement elemento, String texto) {
             elemento.clear();
             elemento.sendKeys(texto);
         }

         public String obterTextoElemento(WebElement elemento) {
             return elemento.getText();
         }

         public boolean elementoEstaVisivel(WebElement elemento) {
             return elemento.isDisplayed();
         }
         
     	public void espera(double segundos) {
    		try {
    			Thread.sleep((long) segundos * 1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
     }