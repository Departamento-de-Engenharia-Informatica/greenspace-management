package pt.ipp.isep.dei.esoft.project.emailSender;

import java.io.InputStream;
import java.util.Properties;

public class EmailConfig extends Properties {
    private Properties properties;

    public EmailConfig(String configFileName) {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + configFileName);
                return;
            }
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
