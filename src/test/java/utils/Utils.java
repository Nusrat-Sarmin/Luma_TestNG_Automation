package utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public void takeScreenShot(WebDriver driver) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }

    //getting user count from JSONArray. or ReadJson. use to LoginPage
    public int getUserCount() throws IOException, ParseException {
        String fileName = "./src/test/resources/Users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        return jsonArray.size() - 1; //Always get tha last value
    }

    public static int generateRandomNumber(int min, int max) {
        int number = (int) Math.floor(Math.random() * (max - min) + min);
        return number;
    }

    //Save data for registration
    public void saveJsonList(String firstName, String lastName, String email, String password) throws IOException, ParseException {
        String fileName = "./src/test/resources/Users.json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userObject = new JSONObject();
        userObject.put("firstname", firstName);
        userObject.put("lastname", lastName);
        userObject.put("email", email);
        userObject.put("password", password);

        jsonArray.add(userObject);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
        System.out.println("Save data");

    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    //Read for User Credential. Read Json
    public void getUserCreds(int pos) throws IOException, ParseException {
        String fileName = "./src/test/resources/Users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject jsonObject = (JSONObject) jsonArray.get(pos);
        setEmail((String) jsonObject.get("email"));
        setPassword((String) jsonObject.get("password"));
    }
}
