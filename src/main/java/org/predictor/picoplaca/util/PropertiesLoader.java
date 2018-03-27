package org.predictor.picoplaca.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for reading a properties file
 *
 * @author martin
 */
public class PropertiesLoader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("messages.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Could not load properties file");
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
