package in.vin.main.constants;

public class ServerConstants {

    public static final String BASE_URL = "https://swapi.co/api/";

    public static final String WEB_SERVICE_PEOPLE = "people/";

    public static final String FORMATE = "?/format=json";

    public static String getHomeWebServiceURL(){
        return BASE_URL+WEB_SERVICE_PEOPLE;
    }


}
