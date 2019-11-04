package by.epam.fitness.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class MembershipPriceReader {
    private static Logger log = LogManager.getLogger(MembershipPriceReader.class);
    private final static String DAYS = "days";
    private final static String PRICE = "price";
    private final static String PRICES_FILE_PATH = "pricelist.json";
    private static Map<Integer, BigDecimal> periodPriceMap;
    private static ReentrantLock lock = new ReentrantLock();
    private static MembershipPriceReader instance;

    private MembershipPriceReader() throws UtilException {
        JsonReader reader = new JsonReader();
        String jsonPrices = reader.read(PRICES_FILE_PATH);
        periodPriceMap = getPeriodPriceMap(jsonPrices);
    }

    public static MembershipPriceReader getInstance() throws UtilException {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new MembershipPriceReader();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private Map<Integer, BigDecimal> getPeriodPriceMap(String jsonPrices) throws UtilException {
        Map<Integer, BigDecimal> periodPriceMap = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonPrices);
            for (Object objectFromArray : jsonArray) {
                JSONObject jsonObject = (JSONObject) objectFromArray;
//                Integer days = (Integer) jsonObject.get(DAYS);
                Integer days = Math.toIntExact((Long) jsonObject.get(DAYS));
                BigDecimal price = BigDecimal.valueOf((Double) jsonObject.get(PRICE));
                periodPriceMap.put(days, price);
            }
        } catch (ParseException e) {
            log.error("Unexpected exception occurred during parsing JSON file with data", e);
            throw new UtilException(e);
        }
        return periodPriceMap;
    }

    public Map<Integer, BigDecimal> getPrices() {
        return periodPriceMap;
    }
}
