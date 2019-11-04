package by.epam.fitness.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Scanner;

public class JsonReader {
    private static Logger log = LogManager.getLogger(JsonReader.class);

    public String read(String pathToFile) throws UtilException {
        if (pathToFile == null) {
            log.error("NULL PATH TO FILE IN READ METHOD");
            throw new UtilException("NULL PATH TO FILE IN READ METHOD");
        }
        StringBuilder resultJsonString = new StringBuilder();
        InputStream file;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            file = classLoader.getResourceAsStream(pathToFile);
        } catch (Exception e) {
            log.error("ERROR WHILE READING FILE: " + pathToFile, e);
            throw new UtilException("ERROR WHILE READING FILE: " + pathToFile, e);
        }
        Scanner scanner;
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            resultJsonString.append(scanner.nextLine()).append("\n");
        }
        return resultJsonString.toString();
    }
}
