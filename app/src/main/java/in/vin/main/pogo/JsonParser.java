package in.vin.main.pogo;

import org.json.JSONException;
import org.json.JSONObject;

import in.vin.main.constants.ApplicationConstants;

public class JsonParser {
    private static final JsonParser ourInstance = new JsonParser();

    public static JsonParser getInstance() {
        return ourInstance;
    }

    private JsonParser() {
    }

    public People getPeopleFromJson(JSONObject jsonObject) throws JSONException {
        People people = new People();
        people.name = jsonObject.getString("name");
        people.height = jsonObject.getString("height");
        people.mass = jsonObject.getString("mass");
        people.hairColor = jsonObject.getString("hair_color");
        people.skinColor = jsonObject.getString("skin_color");
        people.eyeColor = jsonObject.getString("eye_color");
        people.birthYear = jsonObject.getString("birth_year");
        people.gender = jsonObject.getString("gender");
        people.homeWorld = jsonObject.getString("homeworld");
        people.createdDate = jsonObject.getString("created");
        people.editedDate = jsonObject.getString("edited");
        people.url = jsonObject.getString("url");
        people.type = ApplicationConstants.TYPE_PEOPLE;
        return  people;
    }
}
