package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    public class RemoveContactTests extends TestBase {
        @BeforeMethod(alwaysRun = true)
        public void precondition(){
            if (!app.getUser().isLogged()) {
                app.getUser().login(new User()
                        .withEmail("lena.postrash@gmail.com")
                        .withPassword("Mynameislena1!"));

            }
        }


        @Test(priority = 1)
        public void removeOneContact(){
            if(app.getContact().countOfContacts()< 1) return;
            else {
                app.getContact().countOfContacts();
                int res = app.getContact().removeOneContact();
                Assert.assertEquals(res, -1);
            }
        }

        @Test(priority = 2)
        public void removeAllContacts() {
            if (app.getContact().countOfContacts() < 1) return;
            else{
                    app.getContact().removeAllContacts();
                    Assert.assertTrue(!app.getContact().isElementPresent(By.xpath("//div[@class='contact-item_card__2SOIM']")));
                }
        }
    }

