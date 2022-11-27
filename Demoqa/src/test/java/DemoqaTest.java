import PageObjects.BooksPage;
import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class DemoqaTest {

    private BooksPage booksPage;
    private By booksPageUniqueElementByXpath = By.xpath("//div[@class='books-wrapper']");
    private int booksWithTextJavascriptCountNeeded = 10;
    private String publisherToSearch = "O'Reilly Media";
    private String TitlePartToSearch = "Javascript";

    @BeforeTest
    public void SetUp(){
        open("https://demoqa.com/books");
        booksPage = new BooksPage($(booksPageUniqueElementByXpath));
        booksPage.IsOpen();
    }
    @Test
    public void AssertBooks() {
        for (int i = 1; i <= 8; i++){
            $(By.xpath(String.format(booksPage.singleBookTemplate, i))).shouldNotHave(exactText(""));
            $(By.xpath(String.format(booksPage.singleBookImageTemplate, i))).isImage();
        }

        for (int i = 3; i <= 31; i+=4){
            $(By.xpath(String.format(booksPage.singleBookColumnTemplate, i))).shouldNotHave(exactText(""));
        }
    }
    @Test
    public void AssertJavascriptPublisher() throws InterruptedException {
        int booksWithTextJavascript = 0;
//        int imageCol = 1;
//        int titleCol = 2;
//        int AuthorCol = 3;
//        int PublisherCol = 4;

        var allBooks = $$(booksPage.allBooksByXpath);

// Is there a way to make this work?
//        for (var book:
//             allBooks) {
//            if (book.$(By.xpath(String.format("//div[@class='rt-td'])[%d]", PublisherCol))).text().equals("O'Reilly Media")){
//                if (book.$(By.xpath(String.format("//div[@class='rt-td'])[%d]", titleCol))).text().contains("JavaScript")){
//                    booksWithTextJavascript++;
//                }
//            }
//        }

        for (int i = 4; i <= 32; i+=4){
            if ($(By.xpath(String.format(booksPage.singleBookColumnTemplate, i)))
                    .text().equals(publisherToSearch)){

                System.out.println($(By.xpath(String.format(booksPage.singleBookColumnTemplate, i-2)))
                        .text());

                if ($(By.xpath(String.format(booksPage.singleBookColumnTemplate, i-2)))
                        .text().contains(TitlePartToSearch)){

                    booksWithTextJavascript++;
                }

            }
        }

//        Assert.assertEquals(booksWithTextJavascript, booksWithTextJavascriptCountNeeded,
//                String.format("Element collection is not equal to %d", booksWithTextJavascriptCountNeeded));


        SelenideElement searchInput = $(By.id("searchBox"));
        searchInput.clear();
        searchInput.sendKeys("Learning JavaScript Design Patterns");
        SelenideElement bookTitle = $(By.xpath(String.format(booksPage.singleBookColumnTemplate, 2))).shouldHave(text("Learning JavaScript Design Patterns"));
        SelenideElement bookTitleToClick = $(By.xpath(String.format(booksPage.singleBookColumnTemplate, 2) + "//a")).shouldHave(text("Learning JavaScript Design Patterns"));
        Configuration.clickViaJs = true;
        bookTitleToClick.click();
    }
}
