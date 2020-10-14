package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTests {


    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;
    private ResultPage resultPage;

    private final Integer NumberOfCredentials;
    private final List<String> urlList;
    private final List<String> userNameList;
    private final List<String> passwordList;

    public CredentialsTests(){
        this.urlList = new ArrayList<>();
        this.userNameList = new ArrayList<>();
        this.passwordList = new ArrayList<>();
        this.NumberOfCredentials = 3;
    }

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() throws InterruptedException {

        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
        resultPage = new ResultPage(driver);

        // SignUp data
        String firstName = "Mustafa";
        String lastName = "Abbas";
        String userName = "m.abbas";
        String password = "dummyPassword";

        // Creating three set of credentials
        String firstUrl = "www.udacity.com";
        String firstUserName = "Mustafa Abbas";
        String firstPassword = "123";

        String secondUrl = "www.facebook.com";
        String secondUserName = "M.Abbas";
        String secondPassword = "456";

        String thirdUrl = "www.twitter.com";
        String thirdUserName = "Mustafa Abbas Ismail";
        String thirdPassword = "789";


       // List<String> urlList = new ArrayList<>();
        this.urlList.add(firstUrl); this.urlList.add(secondUrl); this.urlList.add(thirdUrl);

        //List<String> userNameList = new ArrayList<>();
        this.userNameList.add(firstUserName); this.userNameList.add(secondUserName); this.userNameList.add(thirdUserName);

       // List<String> passwordList = new ArrayList<>();
        this.passwordList.add(firstPassword); this.passwordList.add(secondPassword); this.passwordList.add(thirdPassword);

        WebDriverWait wait = new WebDriverWait(driver, 5);

        // Go to SignUp page
        loginPage.goToSignup();
        accommodateForPageLoadTime();

        signupPage.signup(firstName, lastName, userName, password);
        accommodateForPageLoadTime();

        driver.get("http://localhost:" + port + "/login");
        accommodateForPageLoadTime();

        loginPage.login(userName, password);
        accommodateForPageLoadTime();

        // Also, creating a note

        for(int i = 0; i < this.NumberOfCredentials; i++)
        {
            homePage.goToCredentialsTab();
            accommodateForPageLoadTime();

            homePage.clickOnAddNewCredential();
            accommodateForPageLoadTime();

            homePage.fillInCredentialForm(urlList.get(i), userNameList.get(i), passwordList.get(i));
            accommodateForPageLoadTime();

            homePage.clickOnSubmitDescriptionButton();
            accommodateForPageLoadTime();

            resultPage.clickHereToGoBack();
            accommodateForPageLoadTime();
        }


    }


    @AfterEach
    public void afterEach() throws InterruptedException {

        driver.get("http://localhost:" + port + "/home");
        accommodateForPageLoadTime();

        // Clearing the lists
        this.urlList.clear();
        this.userNameList.clear();
        this.passwordList.clear();


        // This is in a try - catch block because the delete test won't have any tests to delete
        for(int i = 0; i < this.NumberOfCredentials ; i++)
        {
            try {
                homePage.goToCredentialsTab();
                accommodateForPageLoadTime();

                homePage.clickOnDeleteCredentialButton();
                accommodateForPageLoadTime();

                resultPage.clickHereToGoBack();
                accommodateForPageLoadTime();

            } catch (Exception ignored) {
                ;
            }
        }
    }



    private void accommodateForPageLoadTime() throws InterruptedException {
        Thread.sleep(1000);
    }


    @Test
    public void createCredentials() throws InterruptedException {


        homePage.goToCredentialsTab();
        accommodateForPageLoadTime();

        List<String> retrievedUrls = homePage.getDisplayedCredentialsUrls();
        List<String> retrievedUserNames = homePage.getDisplayedCredentialsUserName();
        List<String> retrievedPasswords = homePage.getDisplayedCredentialsPassword();

        for(int i = 0; i < this.NumberOfCredentials; i++)
        {
            assertEquals(urlList.get(i), retrievedUrls.get(i));
            assertEquals(userNameList.get(i), retrievedUserNames.get(i));
            assertNotEquals(passwordList.get(i), retrievedPasswords.get(i));
        }

    }

    @Test
    public void viewAndEdit() throws InterruptedException {

        homePage.goToCredentialsTab();
        accommodateForPageLoadTime();

        for(int i = 0; i < 1; i++) {
            homePage.clickOnEditCredential(i);
            System.out.println(homePage.getCredentialUrlAfterClickingEdit());
            System.out.println(homePage.getCredentialPasswordAfterClickingEdit());
            System.out.println(homePage.getCredentialUserNameAfterClickingEdit());

        }

    }


    @Test
    public void validateDeletion() throws InterruptedException {

        // Looping for the three deletion buttons
        for(int i = 0; i < this.NumberOfCredentials ; i++)
        {
            homePage.goToCredentialsTab();
            accommodateForPageLoadTime();

            homePage.clickOnDeleteCredentialButton();
            accommodateForPageLoadTime();

            resultPage.clickHereToGoBack();
            accommodateForPageLoadTime();

        }

        // Asserting that no longer 'Delete' buttons are available
        assertThrows(NoSuchElementException.class, () -> homePage.clickOnDeleteCredentialButton());

    }
}
