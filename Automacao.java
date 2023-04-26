

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Automacao {
	
	@Test
	public void testeTextField() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//campo nome
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rafael");
		//campo sobrenome
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pires da Silva");
		//campo radioButton
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		//campo checkBox
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//campo combo
		WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(escolaridade);
		combo.selectByVisibleText("Mestrado");
		//campo comboMultiplaEscolha
		WebElement Esportes = driver.findElement(By.id("elementosForm:esportes"));
		Select comboMulti = new Select(Esportes);
		comboMulti.selectByVisibleText("Futebol");
		comboMulti.selectByVisibleText("Karate");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		//Validacoes
		//proximos comentarios serao outras opcoes que irei utilizar no futuro
		//Assert.assertEquals("Cadastrado!", driver.findElement(By.id("resultado")).getText());
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		
		//Assert.assertEquals("Rafael", driver.findElement(By.id("descNome")).getText());
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Rafael"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Pires da Silva"));  
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));    
		Assert.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Carne"));  
		Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("mestrado"));   
		Assert.assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("Futebol Karate"));   
		Assert.assertTrue(driver.findElement(By.id("descSugestoes")).getText().endsWith("Sugestoes:"));  
		
	}
	
}
