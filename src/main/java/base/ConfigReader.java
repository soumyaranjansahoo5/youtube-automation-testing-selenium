package base;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {
        try {
            prop = new Properties();

            InputStream is = getClass().getClassLoader()
                    .getResourceAsStream("config.properties");

            prop.load(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        String url = prop.getProperty("url");
        System.out.println("Loaded URL: " + url);
        return url;
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }
}