package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

public abstract class BasePage {
    protected SelenideElement uniqueElement;
    protected String pageName;

    public BasePage(SelenideElement uniqueElement, String pageName){
        this.uniqueElement = uniqueElement;
        this.pageName = pageName;
    }

    public void IsOpen() { Assert.assertTrue(uniqueElement.exists(), String.format("%s is not open", pageName)); }
}
