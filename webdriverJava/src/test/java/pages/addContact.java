package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class addContact extends BasePage {
    public addContact(WebDriver navegador) {
        super(navegador);
    }

    public addContact chooseContact(String tipo) {
        navegador.findElement(By.id("addmoredata"));
        WebElement campoType = navegador.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        return this;
    }

    public addContact digitarContact(String contato) {
        navegador.findElement(By.name("contact")).sendKeys(contato);

        return this;
    }

    public MePage clickSave() {
        navegador.findElement(By.linkText("SAVE")).click();

        return new MePage(navegador);
    }
    public MePage adicionarContato(String tipo, String contato) {
        chooseContact(tipo);
        digitarContact(contato);
        clickSave();

        return new MePage(navegador);
    }
}
