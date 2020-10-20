package tests;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        // Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\raimu\\Drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegando para página do taskit!
        navegador.get("http://www.juliodelima.com.br/taskit");

        // Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // identificando o formulário de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com o texto "SIGN IN", tambem da para utilizar 'formularioSignInBox.findElement(By.linkText("SIGN IN")).click();'
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar no link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Cliclar no link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    //@Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        // Cliclar no button atráves do seu xpath //button[@data-target='addmoredata']
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está o formulário de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        // No campo de name "contact" digitar "+5571992314337"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("+5571992314337");

        // Clicar no links de text "SAVE" que está no pop-up
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validaar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Your contact has been added!", mensagem);

    }

    @Test
    public void removerUmContatoDeUmUsuario() {
        // Clicar no elemento xpath "//span[text()="+559123456789"]/following-sibling::a"
        navegador.findElement(By.xpath("//span[text()=\"+559123456789\"]/following-sibling::a")).click();

        // Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        //validar que a mensageem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Rest in peace, dear phone!", mensagem);

        String screensshotArquivo = "C:/Users/Pichau/Documents/test-report/taskit/" + Generator.dataHoraParaArquivo()
                + test.getMethodName() + ".png";

        Screenshot.tirar(navegador, screensshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Cliclar no link com texto "logout"
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown() {
        // Fechar o navegador (O "navegador.quit()" fecha todas as abas, o "navegador.close()" fecha apenas a aba atual)
        //navegador.quit();
    }
}