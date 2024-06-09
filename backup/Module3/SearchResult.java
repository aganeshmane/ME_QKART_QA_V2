package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        WebElement titleElement = parentElement.findElement(
                By.xpath(".//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
        titleOfSearchResult = titleElement.getText();
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizeChart = parentElement.findElement(By.xpath(".//button[text ()='Size chart']"));
            sizeChart.click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of the
             * element is "SIZE CHART". If the text "SIZE CHART" matches for the element, set status
             * = true , else set to false
             */
            WebElement sizeChart =
                    parentElement.findElement(By.xpath(".//button[text ()='Size chart']"));
            if(sizeChart.isDisplayed()){
                String text = sizeChart.getText();
                if(text.toUpperCase().equals("SIZE CHART")){
                    status = true;
                }
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders,
            List<List<String>> expectedTableBody, WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table header in
             * the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body in the
             * same order
             */
            //List<String> expectedTableHeaders = Arrays.asList("Size", "UK/INDIA", "EU", "HEEL TO TOE");


            for(int i = 0; i <expectedTableHeaders.size(); i++){
                String expectedHeaderTable = expectedTableHeaders.get(i);
                int col = i + 1;
                WebElement theaderElement = driver.findElement(By.xpath("//table/thead/tr/th["+col+"]"));
                String actualTableHeader = theaderElement.getText();
                if(!actualTableHeader.equals(expectedHeaderTable)){
                    status = false;
                }
            }
            // List<List<String>> expectedTableBody = Arrays.asList(
            // Arrays.asList("6", "6", "40", "9.8"),
            // Arrays.asList("7", "7", "41", "10.2"), 
            // Arrays.asList("8", "8", "42", "10.6"),
            // Arrays.asList("9", "9", "43", "11"), 
            // Arrays.asList("10", "10", "44", "11.5"),
            // Arrays.asList("11", "11", "45", "12.2"), 
            // Arrays.asList("12", "12", "46", "12.6"));

            for(int i = 0; i <expectedTableBody.size(); i++){
                List<String> rowList = expectedTableBody.get(i);
                for(int j = 0;j<rowList.size();j++){
                    int row = i + 1;
                    int col = j + 1;
                    String expectedBodyValue = rowList.get(j);
                    String actualtbodyValue = driver.findElement(By.xpath("//table/tbody/tr["+ row +"]/td["+ col + "]")).getText();
                    if(!expectedBodyValue.equals(actualtbodyValue)){
                        status = false;
                    }
                }
            }

            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            WebElement sizeDropDown = driver.findElement(By.id("uncontrolled-native"));
            status = sizeDropDown.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}