package tests;


import manager.NgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NgListener.class)

public class LoginTest extends TestBase{
    //    WebDriver wd;

   @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }
//        public void init() {
//        wd = new ChromeDriver();
//        wd.navigate().to("https://telranedu.web.app/home");
//
//        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//    }

    @Test(groups = {"smokeGroup", "sanityGroup", "regressionGroup"})
    public void loginPositiveTest() {
        String email = "lena.postrash@gmail.com", password = "Mynameislena1!";
        User user = new User().withEmail(email).withPassword(password);
//        User user = User.builder()
//                .email(email)
//                .password(password)
//                .build();
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLogged());



    }


//        // open login form
//        wd.findElement(By.xpath("//*[text()='LOGIN']")).click();
//        // fill login form
//        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys("lena.postrash@gmail.com");
//        WebElement passwInput = wd.findElement(By.xpath("//input[2]"));
//        passwInput.click();
//        passwInput.clear();
//        passwInput.sendKeys("Mynameislena1!");
//        // click on login button
//        wd.findElement(By.xpath("//button[1]")).click();
//
//        // Assert
//       // Assert.assertTrue(wd.findElements(By.xpath("//a[@class='active']")).size() > 0);
//       // pause(3000);
//        Assert.assertTrue(isElementPresent(By.xpath("//*[text()='Sign Out']")));
//
//    }

    @Test
    public void loginNegativeTestWrongEmail() {
        String email = "lena.postrashgmail.com", password = "Mynameislena1!";
        // int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        User user = new User().withEmail(email).withPassword(password);
        // open login form
        //wd.findElement(By.xpath("//*[text()='LOGIN']")).click();
        //click(By.xpath("//*[text()='LOGIN']"));
        app.getUser().openLoginRegistrationForm();
        // fill login form
        app.getUser().fillLoginRegistrationForm(user);
//        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys("lena.postrashgmail.com");

//        WebElement passwInput = wd.findElement(By.xpath("//input[2]"));
//        passwInput.click();
//        passwInput.clear();
//        passwInput.sendKeys("Mynameislena1!");
        // click on login button
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isErrorMessageFormatLogin());
        Assert.assertTrue(app.getUser().isAlertPresent());
        //wd.findElement(By.xpath("//button[1]")).click();

        // Assert
    }
}
