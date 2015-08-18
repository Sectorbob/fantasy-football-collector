package net.sectorbob.fantasy.myfantasyleague;

import net.sectorbob.fantasy.model.Player;
import net.sectorbob.fantasy.model.Roster;
import net.sectorbob.fantasy.support.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Kyle Heide
 *
 */
public class FantasyGodsLeagueClient {

    private static Logger LOG = LogManager.getLogger(FantasyGodsLeagueClient.class);

    private String websiteHostName;
    private int year;
    private int leagueId;


    private WebDriver driver;

    public FantasyGodsLeagueClient(String websiteHostName, int year,
                                   int leagueId, ProxyConfiguration proxyConfiguration) {
        this.websiteHostName = websiteHostName;
        this.year = year;
        this.leagueId = leagueId;

        ArrayList<String> phantomArgs = new ArrayList<>();
        phantomArgs.add("--webdriver-loglevel=NONE");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "/usr/local/Cellar/phantomjs/2.0.0/bin/phantomjs");

        // If the proxy is configured then set this up
        if(proxyConfiguration != null) {
            phantomArgs.add("--proxy="+proxyConfiguration.getHost()+":"+proxyConfiguration.getPort());
            phantomArgs.add("--proxy-type=http");
            phantomArgs.add("--proxy-auth="+proxyConfiguration.getUser()+":"+proxyConfiguration.getPassword());
        }

        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);

        driver = new PhantomJSDriver(caps);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(getHomeUrl());
    }

    protected String getBaseUrl() {
        return "http://" + websiteHostName + "/" + year;
    }

    protected String getBaseUrl(int year) {
        return "http://" + websiteHostName + "/" + year;
    }

    protected String getHomeUrl() {
        return getBaseUrl() + "/" + year + "/home/" + leagueId + "#0";
    }

    public void login(WebDriver driver, String teamName, String password) {
        LOG.debug("Starting Login");
        DateTime start = DateTime.now();

        WebElement loginLink = driver.findElement(By.cssSelector("#body_home > table > tbody > tr:nth-child(1) > td.welcome > a"));
        loginLink.click();

        // Select Team
        WebElement teamDropDownListBox = driver.findElement(By.cssSelector("#withmenus > table > tbody > tr > td:nth-child(1) > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > select"));
        Select teamSelect = new Select(teamDropDownListBox);
        teamSelect.selectByVisibleText(teamName);

        // Enter Password
        WebElement passwordTextBox = driver.findElement(By.cssSelector("#withmenus > table > tbody > tr > td:nth-child(1) > form > table > tbody > tr.eventablerow > td:nth-child(2) > input[type=\"password\"]"));
        passwordTextBox.sendKeys(password);

        // CLick Login Button
        WebElement loginButton = driver.findElement(By.cssSelector("#withmenus > table > tbody > tr > td:nth-child(1) > form > p > input[type=\"submit\"]"));
        loginButton.click();

        // Verify Login
        WebElement nameInTopRightB = driver.findElement(By.cssSelector(".welcome > b:nth-child(1)"));

        if( ! nameInTopRightB.getText().equals(teamName)) {
            throw new RuntimeException("Login Didn't work");
        }

        DateTime end = DateTime.now();
        LOG.debug("Finished Login in " + (new Interval(start, end).toDuration().getStandardSeconds()) + "s");

    }

    public void quit() {
        driver.quit();
    }

    public List<Player> getAllPlayersStats(int year) {

        int maxPlayers = 1000;

        String url = getBaseUrl(year) + "/" + "top?L=" + leagueId
                + "&SEARCHTYPE=BASIC&COUNT=" + maxPlayers + "&YEAR=" + year
                + "&START_WEEK=1&END_WEEK=17&CATEGORY=overall&POSITION=*&DISPLAY=points&TEAM=*";
        LOG.debug("Navigating to Player Stats Sheet");
        driver.get(url);
        LOG.debug("Navigated to Player Stats Sheet Successfully");
        Table data = webTableToListMapWithJsoup(driver.getPageSource(), "#withmenus > table > tbody", 1);

        //Util.printTable(LOG, Priority.DEBUG, data, "Player");

        List<Player> players = new ArrayList<>();
        data.forEach((row) -> players.add(Player.fromTableRow(row)));
        return players;
    }

    public List<Roster> getAllRosters(int year) {
        String url = getBaseUrl(year)+"/options?L="+leagueId+"&O=07";
        driver.get(url);

        String html = driver.getPageSource();

        // Get all the roster tables
        Document doc = Jsoup.parse(html);
        List<Roster> rosters = new ArrayList<>();
        doc.select("#withmenus > table > tbody > tr > td > table").forEach((element) -> {
            String teamName = element.select("caption").text();
            Table data = webTableElementToDataTable(element.select("tbody").first(), 0);
            //net.sectorbob.fantasy.support.Util.printTable(LOG, Priority.DEBUG, data, "Player", "2014 YTD Pts", "Bye", "Salary", "Drafted");
            Roster roster = new Roster();
            roster.setTeam(teamName);
            roster.setPlayerIds(new ArrayList<>());
            data.forEach((row) -> {
                try {
                    roster.getPlayerIds().add(Util.extractPlayerIdFromPlayerLinkCell((LinkTableCellData) row.get("Player")));
                } catch(Exception e) {
                    LOG.error("Unknown exceoption when parsing the id from the player link", e);
                }
            });

            rosters.add(roster);
        });

        return rosters;
    }


    // Helpers

    private static Table webTableToListMapWithJsoup(String html, String selector, int ignoreFirstXRows) {
        LOG.debug("Retrieving and parsing data from html");
        Document doc = Jsoup.parse(html);

        Element table = doc.select(selector).first();

        if(table == null) {
            LOG.error("Unable to find table at " + selector + " | Source: " + html);
        }

        Table userTable = webTableElementToDataTable(table, ignoreFirstXRows);

        LOG.debug("Retrieved and parsed data from html successfully");
        return userTable;
    }

    private static Table webTableElementToDataTable(Element tableElement, int ignoreFirstXRows) {
        // create empty table object and iterate through all rows of the found table element
        Table userTable = new Table();
        List<Element> rowElements = tableElement.select("tr");

        // get column names of table from table headers
        List<String> columnNames = new ArrayList<>();
        List<Element> headerElements = rowElements.get(ignoreFirstXRows).select("th");
        for (Element headerElement: headerElements) {
            columnNames.add(headerElement.text());
        }

        // iterate through all rows and add their content to table array
        for (Element rowElement: rowElements) {
            Row row = new Row();

            // add table cells to current row
            int columnIndex = 0;
            List<Element> cellElements = rowElement.select("td");
            for (Element cellElement: cellElements) {
                TableCellData data = extractFromElement(cellElement);
                row.put(columnNames.get(columnIndex), data);
                columnIndex++;
            }

            boolean addRow = false;
            for(Map.Entry<String, TableCellData> entry : row.entrySet()) {
                if(entry.getValue() != null && !entry.getValue().getData().trim().isEmpty()) {
                    addRow = true;
                }
            }

            if(addRow) userTable.add(row);
        }
        return userTable;
    }

    private static TableCellData extractFromElement(Element element) {
        Element a = element.select("a").first();

        if(a == null) {
            return new TableCellData(element.text());
        } else {
            return new LinkTableCellData(element.text(), a.attr("href"));
        }
    }


    public void finalize() {
        this.quit();
    }

}
