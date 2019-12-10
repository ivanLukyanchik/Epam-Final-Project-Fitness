package by.epam.fitness.util.sale;

import by.epam.fitness.util.JsonReader;
import by.epam.fitness.util.UtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Sale system.
 */
public class SaleSystem {
    private static Logger log = LogManager.getLogger(SaleSystem.class);
    private final static String MIN_VISITS = "min visits";
    private final static String MAX_VISITS = "max visits";
    private final static String SALE = "sale";
    private final static String SALES_FILE_PATH = "sales.json";
    private static Map<List<Integer>,Float> visitsSaleMap;
    private static ReentrantLock lock = new ReentrantLock();
    private static SaleSystem instance;

    private SaleSystem() throws UtilException {
        JsonReader dataReader = new JsonReader();
        String jsonPrices = dataReader.read(SALES_FILE_PATH);
        visitsSaleMap = getVisitsSaleMap(jsonPrices);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SaleSystem getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new SaleSystem();
                }
            } catch (UtilException e) {
                log.error("Exception occurred while trying to get instance of SaleSystem", e);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private Map<List<Integer>, Float> getVisitsSaleMap(String jsonSales) throws UtilException {
        Map<List<Integer>,Float> visitsSaleMap = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonSales);
            for (Object objectFromArray : jsonArray) {
                JSONObject jsonObject = (JSONObject) objectFromArray;
                Integer minVisits = Math.toIntExact((Long) jsonObject.get(MIN_VISITS));
                Integer maxVisits = Math.toIntExact((Long) jsonObject.get(MAX_VISITS));
                List<Integer> visitsRange = Arrays.asList(minVisits,maxVisits);
                Float sale = Float.valueOf(String.valueOf(jsonObject.get(SALE)));
                visitsSaleMap.put(visitsRange, sale);
            }
        } catch (ParseException e){
            log.error("Unexpected exception occurred during parsing JSON file with data", e);
            throw new UtilException(e);
        }
        return visitsSaleMap;
    }

    /**
     * Get sale by visit number float.
     *
     * @param visitsNumber the visits number
     * @return the float
     */
    public Float getSaleByVisitNumber(Integer visitsNumber){
        for(List<Integer> visitsRange : visitsSaleMap.keySet()){
            if (visitsNumber >= visitsRange.get(0) && visitsNumber <= visitsRange.get(1)){
                return visitsSaleMap.get(visitsRange);
            }
        }
        return 0f;
    }
}