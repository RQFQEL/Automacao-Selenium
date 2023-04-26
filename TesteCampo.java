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

public class TesteCampo {

	@Test
	public void testeTextField() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

		driver.quit();
	}

	@Test
	public void InteragirComTestArea() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("dhgasdhjgasdjgasdgasjhdgashjdgahsjbd\nteste\nrafa boladao");
		Assert.assertEquals("dhgasdhjgasdjgasdgasjhdgashjdgahsjbd\nteste\nrafa boladao", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

		driver.quit();
	}

	@Test
	public void InteragirComRadioButtom() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

		driver.quit();
	}

	@Test
	public void InteragirComCheckBox() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());

		driver.quit();
	}

	@Test
	public void interagirComCombo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// combo.selectByIndex(3);
		// combo.selectByValue("2graucomp");
		combo.selectByVisibleText("2o grau completo");

		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		driver.quit();
	}

	@Test
	public void verificarValoresCombo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();

		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);

		driver.quit();
	}

	@Test
	public void verificarValoresComboMulti() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("Karate");

		List<WebElement> allselectedOptions = combo.getAllSelectedOptions();

		Assert.assertEquals(3, allselectedOptions.size());
		driver.quit();

	}

	@Test
	public void interagirComBotao() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//WebElement botao = driver.findElement(By.id("buttonSimple"));
		//botao.click();
		
		//Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		driver.findElement(By.id("buttonSimple")).click();
		//driver.findElement(By.id("buttonSimple")).getText();
		Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("Value"));
		
		driver.quit();
	}
	
	@Test
	public void interagirComLinks() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.linkText("Voltar")).click();
		
		driver.quit();
	}
	
	@Test
	public void interagirComAlertSimples() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		
		driver.quit();
	}
	
	@Test
	public void interagirComAlertConfirm() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		alert.accept();
		//alert.accept();
		
		driver.quit();
	}
	
	@Test
	public void interagirComAlertPrompt() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("prompt")).click();
		Alert prompt = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", prompt.getText());
		prompt.sendKeys("Rafa gostosao");
		prompt.accept();
		Assert.assertEquals("Era Rafa gostosao?", prompt.getText());
		prompt.accept();
		Assert.assertEquals(":D", prompt.getText());
		prompt.accept();
		
		//driver.quit();
	}
	
	@Test
	public void buscarTextosNaPagina() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//ira buscar por meio de tags e no site existe muitas tag (metodo com pouca perfomance)
		//Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		
		//ira buscar dentro da tag body um campo que contem o texto a ser validado
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		
		driver.quit();
		
		
	}
}
