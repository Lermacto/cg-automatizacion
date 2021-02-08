package com.gc.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestContactFormService {
    private static final String url = "https://www.consultoriaglobal.com.ar/cgweb/";
    private final ChromeDriver driver;
    private String errorMsgMail = "La dirección e-mail parece inválida.";

    @Autowired
    //Recibe la instancia de ChromeDriver de DriverConfiguration
    public TestContactFormService(ChromeDriver driver) {
        this.driver = driver;
    }

    @PostConstruct
    void postConstruct(){
        sendContactForm("Pepe","emailPepe","TestEmailIncorrecto");
    }

    public void sendContactForm(String name,String email, String asunto){
        try {
            System.out.println("Entrando a pagina");
            driver.get(url);
            System.out.println("Clickeando en Contáctenos");
            driver.findElement(By.linkText("Contáctenos")).click();
            WebElement nameBox = driver.findElement(By.name("your-name"));
            WebElement emailBox = driver.findElement(By.name("your-email"));
            WebElement asuntoBox = driver.findElement(By.name("your-subject"));

            System.out.println("Ingresando datos en campos");
            nameBox.sendKeys(name);
            emailBox.sendKeys(email);
            asuntoBox.sendKeys(asunto);

            System.out.println("Clickeando en Enviar");
            driver.findElement(By.cssSelector("input[type='submit'][value='Enviar']")).click();

            System.out.println("Esperando posibles mensajes de error");
            Thread.sleep(3000);
            //El sleep es necesario para darle tiempo a la pagina a chequear que la informacion dada es valida o no

            System.out.println("Chequeando si el mensaje de error se muestra en la pagina");
            if (driver.findElement(By.xpath("//*[text()='"+errorMsgMail+"']")).isEnabled()){
                System.out.println("Mensaje de error mostrado correctamente");
            }

        }catch (Exception e){
            System.out.println("Sucedio un error en el caso de test");
            e.printStackTrace();
        }
        finally {
            System.out.println("Cerrando WebDriver");
            driver.quit();
        }
    }
}
