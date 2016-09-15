package academy.softserve.edu.utils;

import java.io.FileReader;
import java.util.Properties;

public class PropertiesFile {
    public static String getProperty(String propertyName){
        String propertyValue = null;
            try (final FileReader reader = new FileReader("src/resources/config.properties")) {
                final Properties properties = new Properties();
                properties.load(reader);
             propertyValue = properties.getProperty(propertyName);
            } catch (final Exception e){
                e.printStackTrace();
            }
        return propertyValue;
    }
}
