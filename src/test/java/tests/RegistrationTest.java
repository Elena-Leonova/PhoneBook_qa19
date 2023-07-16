package tests;

import manager.MyDataProvider;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }

    @Test(groups = {"sanityGroup", "regressionGroup"})
    public void registrationPositiveTest() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "lena.postrash" + i + "@gmail.com", password = "Mynameislena1!";
        User user = new User().withEmail(email).withPassword(password);

//            User user = User.builder()
//                    .email(email)
//                    .password(password)
//                    .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        //   Assert.assertTrue(wd.findElements(By.xpath("//a[@href='/add']")).size() > 0);
//            // open login form
//            wd.findElement(By.xpath("//*[text()='LOGIN']")).click();
//            // fill login form
//            WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//            emailInput.click();
//            emailInput.clear();
//            emailInput.sendKeys("lena.postrash" + i + "@gmail.com");
//            WebElement passwInput = wd.findElement(By.xpath("//input[2]"));
//            passwInput.click();
//            passwInput.clear();
//            passwInput.sendKeys("Mynameislena1!");
//            // click on login button
//            wd.findElement(By.xpath("//button[2]")).click();
//
//            // Assert
//           Assert.assertTrue(wd.findElements(By.xpath("//a[@class='active']")).size() > 0);
           Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@class='active']")));
//
//
//        }

    }
    @Test
    public void registrationNegativeTestWrongEmail() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
//        String email = "lena.leonova_" + i + "gmail.com", password = "Mynameislena1!";
        User user = new User().withEmail("lena.leonova_" + i + "gmail.com").withPassword("Mynameislena1!");
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isErrorMessageFormatRegistration());
        Assert.assertTrue(app.getUser().isAlertPresent());

    }


    //  @Test void registrationPositive

    @Test
    public void registrationNegativeTestWrongPassword(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
//          String email = "lena.postrash" + i + "@gmail.com" , password = "lena" ;
        User user = new User().withEmail("lena.postrash" + i + "@gmail.com").withPassword("lena");
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isErrorMessageFormatRegistration());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test(dataProvider = "userModelListDTO_CSV", dataProviderClass = MyDataProvider.class)
    public void registrationPositiveTestDTO(User user){
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@class='active']")));
    }
}
