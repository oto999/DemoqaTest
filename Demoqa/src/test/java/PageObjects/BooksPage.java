package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class BooksPage extends BasePage{

    public String singleBookTemplate = "(//div[@class='rt-tr-group'])[%d]";
    public String singleBookImageTemplate = "(//div[@class='rt-tr-group']//img)[%d]";
    public String singleBookColumnTemplate = "(//div[@class='rt-tr-group']//div[@class='rt-td'])[%d]";
    public By allBooksByXpath = By.xpath(String.format("//div[@class='rt-tr-group']"));


    public BooksPage(SelenideElement uniqueElement){
        super(uniqueElement, "Books page");
    }
}
