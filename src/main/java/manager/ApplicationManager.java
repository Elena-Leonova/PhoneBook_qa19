package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    //  WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;

    HelperContact contact;

    public void init(){
        //wd = new ChromeDriver();
        wd = new EventFiringWebDriver(new ChromeDriver());
        wd.register(new MyListener());
        wd.navigate().to("https://telranedu.web.app/home");
        //wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        user = new HelperUser(wd);
        contact = new HelperContact(wd);
    }

    public void tearDown(){
        wd.quit();
    }

    public HelperUser getUser() {
        return user;
    }

    public HelperContact getContact() {
        return contact;
    }
}
