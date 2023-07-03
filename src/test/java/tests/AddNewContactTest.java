package tests;

import models.Contact;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTest extends TestBase {
    @BeforeMethod
    public void precondition(){
        if (!app.getUser().isLogged()) {
            app.getUser().openLoginRegistrationForm();
            app.getUser().fillLoginRegistrationForm("lena.postrash@gmail.com", "Mynameislena1!");
            app.getUser().submitLogin();
        }
    }

    @Test(invocationCount = 5)
    public void AddNewContactPositive(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Joe")
                .lastName("Doe")
                .phone("123456" + i)
                .email("joe" + i + "@mail.com")
                .address("Haifa")
                .description("Best friend")
                .build();

        app.getContact().openContactForm();
        app.getUser().pause(3000);
        app.getContact().fillContactForm(contact);
        app.getUser().pause(3000);
        app.getContact().submitContactForm();
        app.getUser().pause(3000);

        Assert.assertTrue(
                app.getUser().getText(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]//h3")).equals(contact.getPhone())
        );
    }
}
